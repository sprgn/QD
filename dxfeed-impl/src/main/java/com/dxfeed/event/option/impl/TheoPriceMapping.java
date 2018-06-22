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

public class TheoPriceMapping extends RecordMapping {
// BEGIN: CODE AUTOMATICALLY GENERATED: DO NOT MODIFY. IT IS REGENERATED BY com.dxfeed.api.codegen.ImplCodeGen
	private final int iTime;
	private final int iSequence;
	private final int iPrice;
	private final int iUnderlyingPrice;
	private final int iDelta;
	private final int iGamma;
	private final int iDividend;
	private final int iInterest;

	public TheoPriceMapping(DataRecord record) {
		super(record);
		iTime = MappingUtil.findIntField(record, "Theo.Time", true);
		iSequence = MappingUtil.findIntField(record, "Theo.Sequence", false);
		iPrice = MappingUtil.findIntField(record, "Theo.Price", true);
		iUnderlyingPrice = MappingUtil.findIntField(record, "Theo.UnderlyingPrice", true);
		iDelta = MappingUtil.findIntField(record, "Theo.Delta", true);
		iGamma = MappingUtil.findIntField(record, "Theo.Gamma", true);
		iDividend = MappingUtil.findIntField(record, "Theo.Dividend", false);
		iInterest = MappingUtil.findIntField(record, "Theo.Interest", false);
		putNonDefaultPropertyName("Theo.Time", "Time");
		putNonDefaultPropertyName("Theo.Sequence", "Sequence");
		putNonDefaultPropertyName("Theo.Price", "Price");
		putNonDefaultPropertyName("Theo.UnderlyingPrice", "UnderlyingPrice");
		putNonDefaultPropertyName("Theo.Delta", "Delta");
		putNonDefaultPropertyName("Theo.Gamma", "Gamma");
		putNonDefaultPropertyName("Theo.Dividend", "Dividend");
		putNonDefaultPropertyName("Theo.Interest", "Interest");
	}

	@Deprecated
	public long getTheoTimeMillis(RecordCursor cursor) {
		return getInt(cursor, iTime) * 1000L;
	}

	@Deprecated
	public void setTheoTimeMillis(RecordCursor cursor, long theoTime) {
		setInt(cursor, iTime, TimeUtil.getSecondsFromTime(theoTime));
	}

	@Deprecated
	public int getTheoTimeSeconds(RecordCursor cursor) {
		return getInt(cursor, iTime);
	}

	@Deprecated
	public void setTheoTimeSeconds(RecordCursor cursor, int theoTime) {
		setInt(cursor, iTime, theoTime);
	}

	public long getTimeMillis(RecordCursor cursor) {
		return getInt(cursor, iTime) * 1000L;
	}

	public void setTimeMillis(RecordCursor cursor, long time) {
		setInt(cursor, iTime, TimeUtil.getSecondsFromTime(time));
	}

	public int getTimeSeconds(RecordCursor cursor) {
		return getInt(cursor, iTime);
	}

	public void setTimeSeconds(RecordCursor cursor, int time) {
		setInt(cursor, iTime, time);
	}

	@Deprecated
	public int getTheoSequence(RecordCursor cursor) {
		if (iSequence < 0)
			return 0;
		return getInt(cursor, iSequence);
	}

	@Deprecated
	public void setTheoSequence(RecordCursor cursor, int theoSequence) {
		if (iSequence < 0)
			return;
		setInt(cursor, iSequence, theoSequence);
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

	@Deprecated
	public double getTheoPrice(RecordCursor cursor) {
		return Decimal.toDouble(getInt(cursor, iPrice));
	}

	@Deprecated
	public void setTheoPrice(RecordCursor cursor, double theoPrice) {
		setInt(cursor, iPrice, Decimal.compose(theoPrice));
	}

	@Deprecated
	public int getTheoPriceDecimal(RecordCursor cursor) {
		return getInt(cursor, iPrice);
	}

	@Deprecated
	public void setTheoPriceDecimal(RecordCursor cursor, int theoPrice) {
		setInt(cursor, iPrice, theoPrice);
	}

	public double getPrice(RecordCursor cursor) {
		return Decimal.toDouble(getInt(cursor, iPrice));
	}

	public void setPrice(RecordCursor cursor, double price) {
		setInt(cursor, iPrice, Decimal.compose(price));
	}

	public int getPriceDecimal(RecordCursor cursor) {
		return getInt(cursor, iPrice);
	}

	public void setPriceDecimal(RecordCursor cursor, int price) {
		setInt(cursor, iPrice, price);
	}

	@Deprecated
	public double getTheoUnderlyingPrice(RecordCursor cursor) {
		return Decimal.toDouble(getInt(cursor, iUnderlyingPrice));
	}

	@Deprecated
	public void setTheoUnderlyingPrice(RecordCursor cursor, double theoUnderlyingPrice) {
		setInt(cursor, iUnderlyingPrice, Decimal.compose(theoUnderlyingPrice));
	}

	@Deprecated
	public int getTheoUnderlyingPriceDecimal(RecordCursor cursor) {
		return getInt(cursor, iUnderlyingPrice);
	}

	@Deprecated
	public void setTheoUnderlyingPriceDecimal(RecordCursor cursor, int theoUnderlyingPrice) {
		setInt(cursor, iUnderlyingPrice, theoUnderlyingPrice);
	}

	public double getUnderlyingPrice(RecordCursor cursor) {
		return Decimal.toDouble(getInt(cursor, iUnderlyingPrice));
	}

	public void setUnderlyingPrice(RecordCursor cursor, double underlyingPrice) {
		setInt(cursor, iUnderlyingPrice, Decimal.compose(underlyingPrice));
	}

	public int getUnderlyingPriceDecimal(RecordCursor cursor) {
		return getInt(cursor, iUnderlyingPrice);
	}

	public void setUnderlyingPriceDecimal(RecordCursor cursor, int underlyingPrice) {
		setInt(cursor, iUnderlyingPrice, underlyingPrice);
	}

	@Deprecated
	public double getTheoDelta(RecordCursor cursor) {
		return Decimal.toDouble(getInt(cursor, iDelta));
	}

	@Deprecated
	public void setTheoDelta(RecordCursor cursor, double theoDelta) {
		setInt(cursor, iDelta, Decimal.compose(theoDelta));
	}

	@Deprecated
	public int getTheoDeltaDecimal(RecordCursor cursor) {
		return getInt(cursor, iDelta);
	}

	@Deprecated
	public void setTheoDeltaDecimal(RecordCursor cursor, int theoDelta) {
		setInt(cursor, iDelta, theoDelta);
	}

	public double getDelta(RecordCursor cursor) {
		return Decimal.toDouble(getInt(cursor, iDelta));
	}

	public void setDelta(RecordCursor cursor, double delta) {
		setInt(cursor, iDelta, Decimal.compose(delta));
	}

	public int getDeltaDecimal(RecordCursor cursor) {
		return getInt(cursor, iDelta);
	}

	public void setDeltaDecimal(RecordCursor cursor, int delta) {
		setInt(cursor, iDelta, delta);
	}

	@Deprecated
	public double getTheoGamma(RecordCursor cursor) {
		return Decimal.toDouble(getInt(cursor, iGamma));
	}

	@Deprecated
	public void setTheoGamma(RecordCursor cursor, double theoGamma) {
		setInt(cursor, iGamma, Decimal.compose(theoGamma));
	}

	@Deprecated
	public int getTheoGammaDecimal(RecordCursor cursor) {
		return getInt(cursor, iGamma);
	}

	@Deprecated
	public void setTheoGammaDecimal(RecordCursor cursor, int theoGamma) {
		setInt(cursor, iGamma, theoGamma);
	}

	public double getGamma(RecordCursor cursor) {
		return Decimal.toDouble(getInt(cursor, iGamma));
	}

	public void setGamma(RecordCursor cursor, double gamma) {
		setInt(cursor, iGamma, Decimal.compose(gamma));
	}

	public int getGammaDecimal(RecordCursor cursor) {
		return getInt(cursor, iGamma);
	}

	public void setGammaDecimal(RecordCursor cursor, int gamma) {
		setInt(cursor, iGamma, gamma);
	}

	@Deprecated
	public double getTheoDividend(RecordCursor cursor) {
		if (iDividend < 0)
			return Double.NaN;
		return Decimal.toDouble(getInt(cursor, iDividend));
	}

	@Deprecated
	public void setTheoDividend(RecordCursor cursor, double theoDividend) {
		if (iDividend < 0)
			return;
		setInt(cursor, iDividend, Decimal.compose(theoDividend));
	}

	@Deprecated
	public int getTheoDividendDecimal(RecordCursor cursor) {
		if (iDividend < 0)
			return 0;
		return getInt(cursor, iDividend);
	}

	@Deprecated
	public void setTheoDividendDecimal(RecordCursor cursor, int theoDividend) {
		if (iDividend < 0)
			return;
		setInt(cursor, iDividend, theoDividend);
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

	@Deprecated
	public double getTheoInterest(RecordCursor cursor) {
		if (iInterest < 0)
			return Double.NaN;
		return Decimal.toDouble(getInt(cursor, iInterest));
	}

	@Deprecated
	public void setTheoInterest(RecordCursor cursor, double theoInterest) {
		if (iInterest < 0)
			return;
		setInt(cursor, iInterest, Decimal.compose(theoInterest));
	}

	@Deprecated
	public int getTheoInterestDecimal(RecordCursor cursor) {
		if (iInterest < 0)
			return 0;
		return getInt(cursor, iInterest);
	}

	@Deprecated
	public void setTheoInterestDecimal(RecordCursor cursor, int theoInterest) {
		if (iInterest < 0)
			return;
		setInt(cursor, iInterest, theoInterest);
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
