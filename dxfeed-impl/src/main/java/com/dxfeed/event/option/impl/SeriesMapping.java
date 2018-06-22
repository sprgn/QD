/*
 * !++
 * QDS - Quick Data Signalling Library
 * !-
 * Copyright (C) 2002 - 2018 Devexperts LLC
 * !-
 * This Source Code Form is subject to the terms of the Mozilla Public License, v. 2.0.
 * If a copy of the MPL was not distributed with this file, You can obtain one at
 * http://mozilla.org/MPL/2.0/.
 * !__
 */
package com.dxfeed.event.option.impl;

import com.devexperts.qd.DataRecord;
import com.devexperts.qd.ng.RecordCursor;
import com.devexperts.qd.ng.RecordMapping;
import com.devexperts.qd.util.Decimal;
import com.devexperts.qd.util.MappingUtil;
import com.devexperts.util.TimeUtil;

public class SeriesMapping extends RecordMapping {
// BEGIN: CODE AUTOMATICALLY GENERATED: DO NOT MODIFY. IT IS REGENERATED BY com.dxfeed.api.codegen.ImplCodeGen
	private final int iIndex;
	private final int iTime;
	private final int iSequence;
	private final int iExpiration;
	private final int iVolatility;
	private final int iPutCallRatio;
	private final int iForwardPrice;
	private final int iDividend;
	private final int iInterest;

	public SeriesMapping(DataRecord record) {
		super(record);
		iIndex = MappingUtil.findIntField(record, "Index", false);
		iTime = MappingUtil.findIntField(record, "Time", false);
		iSequence = MappingUtil.findIntField(record, "Sequence", false);
		iExpiration = MappingUtil.findIntField(record, "Expiration", true);
		iVolatility = MappingUtil.findIntField(record, "Volatility", true);
		iPutCallRatio = MappingUtil.findIntField(record, "PutCallRatio", true);
		iForwardPrice = MappingUtil.findIntField(record, "ForwardPrice", true);
		iDividend = MappingUtil.findIntField(record, "Dividend", false);
		iInterest = MappingUtil.findIntField(record, "Interest", false);
	}

	public int getIndex(RecordCursor cursor) {
		if (iIndex < 0)
			return 0;
		return getInt(cursor, iIndex);
	}

	public void setIndex(RecordCursor cursor, int index) {
		if (iIndex < 0)
			return;
		setInt(cursor, iIndex, index);
	}

	public long getTimeMillis(RecordCursor cursor) {
		if (iTime < 0)
			return 0;
		return getInt(cursor, iTime) * 1000L;
	}

	public void setTimeMillis(RecordCursor cursor, long time) {
		if (iTime < 0)
			return;
		setInt(cursor, iTime, TimeUtil.getSecondsFromTime(time));
	}

	public int getTimeSeconds(RecordCursor cursor) {
		if (iTime < 0)
			return 0;
		return getInt(cursor, iTime);
	}

	public void setTimeSeconds(RecordCursor cursor, int time) {
		if (iTime < 0)
			return;
		setInt(cursor, iTime, time);
	}

	public int getSequence(RecordCursor cursor) {
		if (iSequence < 0)
			return 0;
		return getInt(cursor, iSequence);
	}

	public void setSequence(RecordCursor cursor, int sequence) {
		if (iSequence < 0)
			return;
		setInt(cursor, iSequence, sequence);
	}

	public int getExpiration(RecordCursor cursor) {
		return getInt(cursor, iExpiration);
	}

	public void setExpiration(RecordCursor cursor, int expiration) {
		setInt(cursor, iExpiration, expiration);
	}

	public double getVolatility(RecordCursor cursor) {
		return Decimal.toDouble(getInt(cursor, iVolatility));
	}

	public void setVolatility(RecordCursor cursor, double volatility) {
		setInt(cursor, iVolatility, Decimal.compose(volatility));
	}

	public int getVolatilityDecimal(RecordCursor cursor) {
		return getInt(cursor, iVolatility);
	}

	public void setVolatilityDecimal(RecordCursor cursor, int volatility) {
		setInt(cursor, iVolatility, volatility);
	}

	public double getPutCallRatio(RecordCursor cursor) {
		return Decimal.toDouble(getInt(cursor, iPutCallRatio));
	}

	public void setPutCallRatio(RecordCursor cursor, double putCallRatio) {
		setInt(cursor, iPutCallRatio, Decimal.compose(putCallRatio));
	}

	public int getPutCallRatioDecimal(RecordCursor cursor) {
		return getInt(cursor, iPutCallRatio);
	}

	public void setPutCallRatioDecimal(RecordCursor cursor, int putCallRatio) {
		setInt(cursor, iPutCallRatio, putCallRatio);
	}

	public double getForwardPrice(RecordCursor cursor) {
		return Decimal.toDouble(getInt(cursor, iForwardPrice));
	}

	public void setForwardPrice(RecordCursor cursor, double forwardPrice) {
		setInt(cursor, iForwardPrice, Decimal.compose(forwardPrice));
	}

	public int getForwardPriceDecimal(RecordCursor cursor) {
		return getInt(cursor, iForwardPrice);
	}

	public void setForwardPriceDecimal(RecordCursor cursor, int forwardPrice) {
		setInt(cursor, iForwardPrice, forwardPrice);
	}

	public double getDividend(RecordCursor cursor) {
		if (iDividend < 0)
			return Double.NaN;
		return Decimal.toDouble(getInt(cursor, iDividend));
	}

	public void setDividend(RecordCursor cursor, double dividend) {
		if (iDividend < 0)
			return;
		setInt(cursor, iDividend, Decimal.compose(dividend));
	}

	public int getDividendDecimal(RecordCursor cursor) {
		if (iDividend < 0)
			return 0;
		return getInt(cursor, iDividend);
	}

	public void setDividendDecimal(RecordCursor cursor, int dividend) {
		if (iDividend < 0)
			return;
		setInt(cursor, iDividend, dividend);
	}

	public double getInterest(RecordCursor cursor) {
		if (iInterest < 0)
			return Double.NaN;
		return Decimal.toDouble(getInt(cursor, iInterest));
	}

	public void setInterest(RecordCursor cursor, double interest) {
		if (iInterest < 0)
			return;
		setInt(cursor, iInterest, Decimal.compose(interest));
	}

	public int getInterestDecimal(RecordCursor cursor) {
		if (iInterest < 0)
			return 0;
		return getInt(cursor, iInterest);
	}

	public void setInterestDecimal(RecordCursor cursor, int interest) {
		if (iInterest < 0)
			return;
		setInt(cursor, iInterest, interest);
	}
// END: CODE AUTOMATICALLY GENERATED
}
