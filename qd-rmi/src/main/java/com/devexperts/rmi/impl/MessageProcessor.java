/*
 * !++
 * QDS - Quick Data Signalling Library
 * !-
 * Copyright (C) 2002 - 2017 Devexperts LLC
 * !-
 * This Source Code Form is subject to the terms of the Mozilla Public License, v. 2.0.
 * If a copy of the MPL was not distributed with this file, You can obtain one at
 * http://mozilla.org/MPL/2.0/.
 * !__
 */
package com.devexperts.rmi.impl;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.Executor;

import com.devexperts.connector.proto.EndpointId;
import com.devexperts.connector.proto.JVMId;
import com.devexperts.io.*;
import com.devexperts.logging.Logging;
import com.devexperts.rmi.*;
import com.devexperts.rmi.message.*;
import com.devexperts.rmi.task.*;
import com.devexperts.util.LongHashMap;
import com.devexperts.util.SystemProperties;

/**
 * Auxiliary class that processes incoming RMI messages and keeps processed stream state.
 */
class MessageProcessor {
	private static final Logging log = Logging.getLogging(MessageProcessor.class);

	private static final int MAX_LENGTH_RMI_ROUTE = SystemProperties.getIntProperty("com.devexperts.rmi.maxLengthRMIRoute", 10);

	// ==================== private fields ====================

	private final RMIConnection connection;
	private final Subjects subjects = new Subjects();
	private final Operations operations = new Operations();

	// ==================== constructor ====================

	MessageProcessor(RMIConnection connection) {
		this.connection = connection;
	}

	// ==================== process messages ====================

	void processComboRequestMessage(BufferedInput data) throws IOException {
		long requestId = data.readCompactLong();
		RMIMessageKind kind = RMIMessageKind.readFromRequest(data);
		//if channelId = 0 => top-level request
		long channelId = kind.hasChannel() ? data.readCompactLong() : 0;
		RMIRequestType requestType = RMIRequestType.readFromRequest(data);
		try {
			JVMId.ReadContext ctx = new JVMId.ReadContext();
			RMIRoute route = parseRoute(data, ctx);
			RMIServiceId target = RMIServiceId.readRMIServiceId(data, ctx);
			int subjectId = data.readCompactInt();
			int operationId = data.readCompactInt();
			Marshalled<Object[]> parametersRequest = null;
			RMIOperation<?> operation = operations.getOperation(operationId);
			if (operation != null)
				parametersRequest = data.readMarshalled(operation.getParametersMarshaller(), connection.endpoint.getSerialClassContext());
			makeTask(channelId, requestId, subjectId, operationId, kind, parametersRequest, requestType, route, target);
		} catch (IOException e) {
			makeFailedTask(RMIExceptionType.FAILED_TO_READ_REQUEST, "Failed read request", kind, requestType, channelId, requestId);
		}
	}

	@SuppressWarnings("unchecked")
	void processComboResponseMessage(BufferedInput data) throws IOException {
		long requestId = data.readCompactLong();
		RMIMessageKind kind = RMIMessageKind.readFromResponse(data);
		//if channelId = 0 => top-level request
		long channelId = kind.hasChannel() ? data.readCompactLong() : 0;
		RMIRequestImpl<?> request = retrieveRequest(channelId, requestId, kind);
		if (request == null)
			return;
		RMIRoute route = parseRoute(data, new JVMId.ReadContext());
		Marshaller<?> marshaller = kind.isError() ? RMIResponseMessage.getExceptionMarshaller() :
			request.getOperation().getResultMarshaller();
		Marshalled<?> resultMarshalled = data.readMarshalled(marshaller, connection.endpoint.getSerialClassContext());
		if (kind.isError())
			request.setFailedState((Marshalled<RMIException>)resultMarshalled, route);
		else
			request.setSucceededState(resultMarshalled, route);
	}

	void processDescribeSubjectMessage(BufferedInput data) throws IOException {
		int subjectId = data.readCompactInt();
		Marshalled<Object> subject = data.readMarshalled(Marshaller.SERIALIZATION, connection.endpoint.getSerialClassContext());
		subjects.putSubject(subjectId, subject);
	}

	void processDescribeOperationMessage(BufferedInput data) throws IOException {
		int operationId = data.readCompactInt();
		String signature = data.readUTFString();
		operations.putOperation(operationId, RMIOperation.valueOf(signature));
	}

	void processAdvertiseServicesMessage(BufferedInput data) throws IOException {
		RMIServiceId serviceId;
		int distance;
		Map<String, String> props;
		JVMId.ReadContext ctx = new JVMId.ReadContext();
		RMIServiceDescriptor descriptor;
		List<RMIServiceDescriptor> descriptors = new ArrayList<>();
		while (data.hasAvailable()) {
			serviceId = RMIServiceId.readRMIServiceId(data, ctx);
			distance = data.readCompactInt();
			int nIntermediateNodes = data.readCompactInt();
			Set<EndpointId> intermediateNodes = new HashSet<>(nIntermediateNodes + 1);
			for (int j = 0; j < nIntermediateNodes; j++)
				intermediateNodes.add(EndpointId.readEndpointId(data, ctx));
			intermediateNodes.add(connection.endpoint.getEndpointId());
			int nProps = data.readCompactInt();
			props = new HashMap<>();
			for (int j = 0; j < nProps; j++)
				props.put(data.readUTFString(), data.readUTFString());
			if (connection.configuredServices.accept(serviceId.getName())) {
				if (distance == RMIService.UNAVAILABLE_METRIC)
					descriptor = RMIServiceDescriptor.createUnavailableDescriptor(serviceId, props);
				else
					descriptor = RMIServiceDescriptor.createDescriptor(serviceId, distance + connection.weight, intermediateNodes, props);
				descriptors.add(descriptor);
			}
		}
		if (RMIEndpointImpl.RMI_TRACE_LOG)
			log.trace("Process advertise services " + descriptors + " at " + connection);
		connection.endpoint.getClient().updateServiceDescriptors(descriptors, connection);
	}

	private RMIRoute parseRoute(BufferedInput data, JVMId.ReadContext ctx) throws IOException {
		int size = data.readCompactInt();
		//empty route is not sent over the network
		if (size < 0)
			throw new IOException("The size of the route request can not be negative");
		EndpointId[] result = new EndpointId[size + 1];
		for (int i = 0; i < size; i++)
			result[i] = EndpointId.readEndpointId(data, ctx);
		result[size] = connection.getRemoteEndpointId();
		return RMIRoute.createRMIRoute(result);
	}

	// ==================== process legacy (backwards-compatible) messages ====================

	void processOldRequestMessage(BufferedInput data) throws IOException {
		long requestId = data.readCompactLong();
		int channelId = 0;
		int reqTypeId = data.readCompactInt();
		// last 4 bits are reserved for request type
		RMIRequestType requestType = RMIRequestType.getById(reqTypeId & RMIMessageConstants.REQUEST_TYPE_MASK);
		if (requestType == null)
			throw new IOException("Failed to read request type");
		try {
			JVMId.ReadContext ctx = new JVMId.ReadContext();
			//use 6th bit as route flag
			RMIRoute route = (reqTypeId & RMIMessageConstants.REQUEST_WITH_ROUTE) != 0 ? parseRoute(data, ctx) :
				RMIRoute.createRMIRoute(connection.getRemoteEndpointId());
			//use 5th bit as target flag
			RMIServiceId target = (reqTypeId & RMIMessageConstants.REQUEST_WITH_TARGET) != 0 ?
				RMIServiceId.readRMIServiceId(data, ctx) : null;
			int subjectId = data.readCompactInt();
			int operationId = data.readCompactInt();

			Marshalled<Object[]> parametersRequest = null;
			RMIOperation<?> operation = operations.getOperation(operationId);
			if (operation != null)
				parametersRequest = data.readMarshalled(operation.getParametersMarshaller(), connection.endpoint.getSerialClassContext());

			makeTask(channelId, requestId, subjectId, operationId, RMIMessageKind.REQUEST, parametersRequest, requestType, route, target);
		} catch (IOException e) {
			makeFailedTask(RMIExceptionType.FAILED_TO_READ_REQUEST,"Failed read request", RMIMessageKind.REQUEST, requestType, channelId, requestId);
		}
	}

	void processOldResultMessage(BufferedInput data) throws IOException {
		long requestId = data.readCompactLong();
		int channelId = 0;
		RMIRequestImpl<?> request = retrieveRequest(channelId, requestId, null);
		if (request == null)
			return;
		Marshalled<?> resultMarshalled = data.readMarshalled(request.getOperation().getResultMarshaller(),
			connection.endpoint.getSerialClassContext());
		RMIRoute route = null;
		if (data.hasAvailable())
			route = parseRoute(data, new JVMId.ReadContext());
		if (route == null)
			route = RMIRoute.createRMIRoute(connection.getRemoteEndpointId());
		request.setSucceededState(resultMarshalled, route);
	}

	void processOldErrorMessage(BufferedInput data) throws IOException {
		long requestId = data.readCompactLong();
		int channelId = 0;
		RMIRequestImpl<?> request = retrieveRequest(channelId, requestId, null);
		if (request == null)
			return;
		Marshalled<RMIException> marshalledException = data.readMarshalled(RMIResponseMessage.getExceptionMarshaller(),
			connection.endpoint.getSerialClassContext());
		RMIRoute route = null;
		if (data.hasAvailable())
			route = parseRoute(data, new JVMId.ReadContext());
		if (route == null)
			route = RMIRoute.createRMIRoute(connection.getRemoteEndpointId());
		request.setFailedState(marshalledException, route);
	}

	//if channelId = 0 => top-level request
	void processOldCancelMessage(BufferedInput data) throws IOException {
		long requestId = data.readCompactLong();
		int cancellationFlags = data.readCompactInt();
		connection.tasksManager.cancelTask(requestId, 0, cancellationFlags, null);
	}

	// ==================== other methods ====================

	@SuppressWarnings("unchecked")
	void createAndSubmitTask(RMIChannelImpl channel, ServerRequestInfo requestInfo) {
		RMIService<?> service;
		boolean nestedTask = requestInfo.channelId != 0;
		if (nestedTask) {
			assert channel != null;
			service = channel.getHandler(requestInfo.message.getOperation().getServiceName());
		} else {
			assert channel == null;
			service = connection.endpoint.getServer().getProvidedService(requestInfo.message.getTarget());
		}

		if (service == null) {
			makeFailedTask(RMIExceptionType.UNKNOWN_SERVICE, "\"" + requestInfo.message.getOperation().getServiceName() + "\"",
				requestInfo.kind, requestInfo.message.getRequestType(), requestInfo.channelId, requestInfo.reqId);
			return;
		}

		// ----------------------------------------
		// NOTE: We need method makeFailedTask only before this point, after it we have a RMITaskImpl instance,
		// that knows how to propagated all results and failures to the requesting peers

		RMITaskImpl<?> task = nestedTask ?
			RMITaskImpl.createNestedTask(requestInfo.message, connection, channel, requestInfo.reqId) :
			RMITaskImpl.createTopLevelTask(requestInfo.subject, requestInfo.message, connection, requestInfo.reqId);

		if (RMIEndpointImpl.RMI_TRACE_LOG)
			log.trace("Create task " + task + " at " + connection);

		connection.tasksManager.registerTask(task);
		Executor serviceExecutor = service.getExecutor();
		Executor executor = serviceExecutor;
		if (executor == null) {
			executor = nestedTask ? channel.getExecutor() : connection.endpoint.getServer().getDefaultExecutor();
		}
		task.setExecutor(executor);
		// create and enqueue execution task
		RMIExecutionTaskImpl<?> executionTask =
			new RMIExecutionTaskImpl(requestInfo.reqId, connection, task, service, executor);
		executionTask.enqueueForSubmissionSerially();
	}

	// ==================== private methods ====================

	//if channelId = 0 => top-level request
	private RMIRequestImpl<?> retrieveRequest(long channelId, long requestId, RMIMessageKind kind) {
		RMIRequestImpl<?> request = connection.requestsManager.removeSentRequest(channelId, requestId, kind);
		if (request == null)
			log.error("No request with request ID#" + requestId +
				(channelId != 0 ? " (channel ID#" + channelId + ")" : "") +
				" was pending for execution");
		return request;
	}

	@SuppressWarnings("unchecked")
	//if channelId = 0 => top-level request
	private void makeTask(long channelId, long curReqId, int subjectId, int operationId,
		RMIMessageKind kind, Marshalled<Object[]> parametersRequest, RMIRequestType requestType, RMIRoute route,
		RMIServiceId target)
	{
		Marshalled<?> marshalledSubject = subjects.getSubject(subjectId);
		if (marshalledSubject == null) {
			makeFailedTask(RMIExceptionType.UNKNOWN_SUBJECT, "#" + subjectId, kind, requestType, channelId, curReqId);
			return;
		}
		RMIOperation<?> operation = operations.getOperation(operationId);
		if (operation == null) {
			makeFailedTask(RMIExceptionType.UNKNOWN_OPERATION, "#" + operationId, kind, requestType, channelId, curReqId);
			return;
		}
		boolean nestedTask = channelId != 0;
		if (!nestedTask && !connection.configuredServices.accept(operation.getServiceName())) {
			makeFailedTask(RMIExceptionType.UNKNOWN_SERVICE,
				"\"" + operation.getServiceName() + "\"", kind, requestType, channelId, curReqId);
			return;
		}
		if (!route.isEmpty()) {
			EndpointId lastNode = route.getLast();
			if (route.indexOf(lastNode) != route.size() - 1 || route.size() >= MAX_LENGTH_RMI_ROUTE) {
				makeFailedTask(RMIExceptionType.ROUTE_IS_NOT_FOUND,
					"Request for \"" + operation.getServiceName() + "\" got into routing loop: Route = " + route,
					kind, requestType, channelId, curReqId);
				return;
			}
		}

		if (checkCancelRequest(channelId, parametersRequest, operation, kind))
			return;

		RMIRequestMessage<?> requestMessage =
			new RMIRequestMessage<>(requestType, operation, parametersRequest, route, target);
		if (target == null && !nestedTask) {
			target = connection.endpoint.getServer().loadBalance(requestMessage);
			requestMessage = requestMessage.changeTargetRoute(target, route);
		}

		ServerRequestInfo requestInfo = new ServerRequestInfo(kind, curReqId, channelId, requestMessage, marshalledSubject);
		if (nestedTask) {
			RMIChannelImpl channel = connection.channelsManager.getChannel(
				channelId, kind.hasClient() ? RMIChannelType.CLIENT_CHANNEL : RMIChannelType.SERVER_CHANNEL);
			boolean ok;
			if (channel == null) {
				ok = false;
			} else {
				ok = channel.addIncomingRequest(requestInfo);
			}
			if (!ok) {
				RMILog.logFailedTask(RMIExceptionType.CHANNEL_CLOSED,
					". The channel number " + requestInfo.channelId + " has already been closed or never existed",
					connection, requestInfo.reqId, requestInfo.channelId, requestInfo.message.getRequestType());
				return;
			}
		} else {
			createAndSubmitTask(null, requestInfo);
		}
	}

	private boolean checkCancelRequest(long channelId, Marshalled<Object[]> parametersRequest, RMIOperation<?> operation, RMIMessageKind kind) {
		if (!RMIRequestImpl.isCancelOperation(operation))
			return false;
		Object[] params = parametersRequest.getObject();
		RMIChannelType channelType = kind.hasClient() ? RMIChannelType.CLIENT_CHANNEL : RMIChannelType.SERVER_CHANNEL;
		if ((long)params[0] == 0L) {
			RMIChannelImpl channel = connection.channelsManager.getChannel(channelId, channelType);
			if (channel != null)
				channel.cancel(RMICancelType.valueOf(operation.getMethodName()));
		} else {
			connection.tasksManager.cancelTask((long)params[0], channelId, RMICancelType.valueOf(operation.getMethodName()).getId(), channelType);
		}
		return true;
	}

	private void makeFailedTask(RMIExceptionType exceptionType, String info, RMIMessageKind kind, RMIRequestType requestType,
		long channelId, long requestId)
	{
		RMILog.logFailedTask(exceptionType, info, connection, requestId, channelId, requestType);
		connection.tasksManager.notifyTaskCompleted(requestType,
			new RMITaskResponse(new RMIErrorMessage(exceptionType, new RMIFailedException(info), null),
				channelId, requestId, kind.hasClient() ? RMIChannelType.CLIENT_CHANNEL : RMIChannelType.SERVER_CHANNEL ));
	}

	// ==================== nested helper class ====================

	private class Subjects {
		private LongHashMap<Marshalled<Object>> map;
		private Marshalled<Object> defaultSubject;

		Marshalled<Object> getSubject(int subjectId) {
			if (subjectId == 0) {
				if (defaultSubject == null) {
					Object connectionSubject = connection.getSubject();
					defaultSubject = connectionSubject != null ? Marshalled.forObject(connectionSubject) : Marshalled.NULL;
				}
				return defaultSubject;
			}
			return map == null ? null : map.get(subjectId);
		}

		void putSubject(int id, Marshalled<Object> subject) {
			if (map == null)
				map = new LongHashMap<>();
			map.put(id, subject);
		}
	}

	private static class Operations {
		private final LongHashMap<RMIOperation<?>> map = new LongHashMap<>();

		RMIOperation<?> getOperation(int operationId) {
			return map.get(operationId);
		}

		void putOperation(int id, RMIOperation<?> operation) {
			map.put(id, operation);
		}
	}
}
