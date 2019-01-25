/*
 * !++
 * QDS - Quick Data Signalling Library
 * !-
 * Copyright (C) 2002 - 2019 Devexperts LLC
 * !-
 * This Source Code Form is subject to the terms of the Mozilla Public License, v. 2.0.
 * If a copy of the MPL was not distributed with this file, You can obtain one at
 * http://mozilla.org/MPL/2.0/.
 * !__
 */
package com.devexperts.qd.impl.stripe;

import com.devexperts.qd.*;
import com.devexperts.qd.impl.AbstractAgent;
import com.devexperts.qd.ng.*;
import com.devexperts.qd.stats.QDStats;

class StripedAgent<C extends QDCollector> extends AbstractAgent {
	private final StripedCollector<C> collector;
	private final int n;
	private final QDAgent[] agents;
	private final Provider provider;
	private volatile Provider snapshotProvider; // lazy init

	StripedAgent(StripedCollector<C> collector, Builder builder) {
		super(collector.getContract(), builder);
		this.collector = collector;
		n = collector.n;
		agents = new QDAgent[n];
		for (int i = 0; i < n; i++)
			agents[i] = collector.collectors()[i].buildAgent(builder);
		provider = new Provider(false);
	}

	@Override
	public void addSubscription(RecordSource source) {
		RecordBuffer[] buf = Buffers.filterSub(collector, source);
		for (int i = 0; i < n; i++) {
			if (buf[i] != null && !buf[i].isEmpty()) {
				agents[i].addSubscription(buf[i]);
				buf[i].clear();
			}
		}
		Buffers.buf.set(buf);
	}

	@Override
	public void removeSubscription(RecordSource source) {
		RecordBuffer[] buf = Buffers.filterSub(collector, source);
		for (int i = 0; i < n; i++) {
			if (buf[i] != null && !buf[i].isEmpty()) {
				agents[i].removeSubscription(buf[i]);
				buf[i].clear();
			}
		}
		Buffers.buf.set(buf);
	}

	@Override
	public void setSubscription(RecordSource source) {
		RecordBuffer[] buf = Buffers.filterSub(collector, source);
		for (int i = 0; i < n; i++) {
			agents[i].setSubscription(buf[i] == null ? RecordSource.VOID : buf[i]);
			if (buf[i] != null)
				buf[i].clear();
		}
		Buffers.buf.set(buf);
	}

	@Override
	public void close() {
		for (int i = 0; i < n; i++)
			agents[i].close();
	}

	@Override
	public void closeAndExamineDataBySubscription(RecordSink sink) {
		for (int i = 0; i < n; i++)
			agents[i].closeAndExamineDataBySubscription(sink);
	}

	@Override
	public QDStats getStats() {
		return agents[0].getStats();
	}

	@Override
	public void setBufferOverflowStrategy(BufferOverflowStrategy bufferOverflowStrategy) {
		for (int i = 0; i < n; i++)
			agents[i].setBufferOverflowStrategy(bufferOverflowStrategy);
	}

	@Override
	public void setMaxBufferSize(int maxBufferSize) {
		for (int i = 0; i < n; i++)
			agents[i].setMaxBufferSize(maxBufferSize);
	}

	@Override
	public boolean retrieve(RecordSink sink) {
		return provider.retrieve(sink);
	}

	@Override
	public void setRecordListener(RecordListener listener) {
		provider.setRecordListener(listener);
	}

	@Override
	public RecordProvider getSnapshotProvider() {
		Provider snapshotProvider = this.snapshotProvider;
		if (snapshotProvider != null)
			return snapshotProvider;
		return getSnapshotProviderSync();
	}

	private synchronized RecordProvider getSnapshotProviderSync() {
		if (snapshotProvider != null)
			return snapshotProvider;
		return snapshotProvider = new Provider(true);
	}

	@Override
	public boolean isSubscribed(DataRecord record, int cipher, String symbol, long time) {
		return agents[collector.index(cipher, symbol)].isSubscribed(record, cipher, symbol, time);
	}

	@Override
	public boolean examineSubscription(RecordSink sink) {
		for (int i = 0; i < n; i++) {
			if (agents[i].examineSubscription(sink))
				return true;
		}
		return false;
	}

	@Override
	public int getSubscriptionSize() {
		int sum = 0;
		for (int i = 0; i < n; i++)
			sum += agents[i].getSubscriptionSize();
		return sum;
	}

	private class Provider extends AbstractRecordProvider {
		private final StripedNotification notify;
		private final RecordProvider[] providers;
		private final RecordProvider notificationProvider;
		private volatile RecordListener listener;

		Provider(boolean snapshot) {
			this.notify = new StripedNotification(n);
			if (snapshot) {
				providers = new RecordProvider[n];
				for (int i = 0; i < n; i++)
					providers[i] = agents[i].getSnapshotProvider();
				notificationProvider = this;
			} else {
				providers = agents;
				notificationProvider = StripedAgent.this;
			}
			for (int i = 0; i < n; ++i)
				providers[i].setRecordListener(new Listener(i));
		}

		@Override
		public RecordMode getMode() {
			return StripedAgent.this.getMode();
		}

		@Override
		public boolean retrieve(RecordSink sink) {
			int i;
			while ((i = notify.next()) >= 0)
				if (providers[i].retrieve(sink)) {
					notify.notify(i);
					return true;
				}
			return false;
		}

		@Override
		public void setRecordListener(RecordListener listener) {
			boolean wasVoid = this.listener == RecordListener.VOID;
			boolean nowVoid = listener == RecordListener.VOID;
			this.listener = listener;
			if (nowVoid != wasVoid)
				for (int i = 0; i < n; ++i)
					providers[i].setRecordListener(nowVoid ? RecordListener.VOID : new Listener(i));
			if (notify.hasNext())
				notifyListener();
		}

		private void notifyListener() {
			RecordListener listener = this.listener;
			if (listener != null)
				listener.recordsAvailable(notificationProvider);
		}

		void recordsAvailable(int i) {
			if (notify.notify(i))
				notifyListener();
		}

		private class Listener implements RecordListener {
			private final int i;

			Listener(int i) {
				this.i = i;
			}

			@Override
			public void recordsAvailable(RecordProvider provider) {
				Provider.this.recordsAvailable(i);
			}
		}
	}
}
