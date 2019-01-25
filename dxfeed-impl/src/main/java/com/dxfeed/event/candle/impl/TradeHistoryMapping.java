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
package com.dxfeed.event.candle.impl;

import com.devexperts.qd.DataRecord;
import com.devexperts.qd.ng.RecordCursor;
import com.devexperts.qd.util.Decimal;
import com.devexperts.qd.util.MappingUtil;
import com.devexperts.util.TimeUtil;

public class TradeHistoryMapping extends CandleEventMapping {
// BEGIN: CODE AUTOMATICALLY GENERATED: DO NOT MODIFY. IT IS REGENERATED BY com.dxfeed.api.codegen.ImplCodeGen
	private final int iTime;
	private final int iSequence;
	private final int iExchangeCode;
	private final int iClose;
	private final int iVolume;
	private final int iBidPrice;
	private final int iAskPrice;

	public TradeHistoryMapping(DataRecord record) {
		super(record);
		iTime = MappingUtil.findIntField(record, "Time", true);
		iSequence = MappingUtil.findIntField(record, "Sequence", true);
		iExchangeCode = MappingUtil.findIntField(record, "Exchange", false);
		iClose = findIntField("Price", true);
		iVolume = findIntField("Size", true);
		iBidPrice = findIntField("Bid", false);
		iAskPrice = findIntField("Ask", false);
		putNonDefaultPropertyName("Exchange", "ExchangeCode");
		putNonDefaultPropertyName("Price", "Close");
		putNonDefaultPropertyName("Size", "Volume");
		putNonDefaultPropertyName("Bid", "BidPrice");
		putNonDefaultPropertyName("Ask", "AskPrice");
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

	public int getSequence(RecordCursor cursor) {
		return getInt(cursor, iSequence);
	}

	public void setSequence(RecordCursor cursor, int sequence) {
		setInt(cursor, iSequence, sequence);
	}

	@Deprecated
	public char getExchange(RecordCursor cursor) {
		if (iExchangeCode < 0)
			return '\0';
		return (char)getInt(cursor, iExchangeCode);
	}

	@Deprecated
	public void setExchange(RecordCursor cursor, char exchange) {
		if (iExchangeCode < 0)
			return;
		setInt(cursor, iExchangeCode, exchange);
	}

	public char getExchangeCode(RecordCursor cursor) {
		if (iExchangeCode < 0)
			return '\0';
		return (char)getInt(cursor, iExchangeCode);
	}

	public void setExchangeCode(RecordCursor cursor, char exchangeCode) {
		if (iExchangeCode < 0)
			return;
		setInt(cursor, iExchangeCode, exchangeCode);
	}

	@Deprecated
	public double getPrice(RecordCursor cursor) {
		return getAsDouble(cursor, iClose);
	}

	@Deprecated
	public void setPrice(RecordCursor cursor, double price) {
		setAsDouble(cursor, iClose, price);
	}

	@Deprecated
	public int getPriceDecimal(RecordCursor cursor) {
		return getAsTinyDecimal(cursor, iClose);
	}

	@Deprecated
	public void setPriceDecimal(RecordCursor cursor, int price) {
		setAsTinyDecimal(cursor, iClose, price);
	}

	@Deprecated
	public long getPriceWideDecimal(RecordCursor cursor) {
		return getAsWideDecimal(cursor, iClose);
	}

	@Deprecated
	public void setPriceWideDecimal(RecordCursor cursor, long price) {
		setAsWideDecimal(cursor, iClose, price);
	}

	public double getClose(RecordCursor cursor) {
		return getAsDouble(cursor, iClose);
	}

	public void setClose(RecordCursor cursor, double close) {
		setAsDouble(cursor, iClose, close);
	}

	public int getCloseDecimal(RecordCursor cursor) {
		return getAsTinyDecimal(cursor, iClose);
	}

	public void setCloseDecimal(RecordCursor cursor, int close) {
		setAsTinyDecimal(cursor, iClose, close);
	}

	public long getCloseWideDecimal(RecordCursor cursor) {
		return getAsWideDecimal(cursor, iClose);
	}

	public void setCloseWideDecimal(RecordCursor cursor, long close) {
		setAsWideDecimal(cursor, iClose, close);
	}

	@Deprecated
	public int getSize(RecordCursor cursor) {
		return getAsInt(cursor, iVolume);
	}

	@Deprecated
	public void setSize(RecordCursor cursor, int size) {
		setAsInt(cursor, iVolume, size);
	}

	@Deprecated
	public long getSizeLong(RecordCursor cursor) {
		return getAsLong(cursor, iVolume);
	}

	@Deprecated
	public void setSizeLong(RecordCursor cursor, long size) {
		setAsLong(cursor, iVolume, size);
	}

	@Deprecated
	public double getSizeDouble(RecordCursor cursor) {
		return getAsDouble(cursor, iVolume);
	}

	@Deprecated
	public void setSizeDouble(RecordCursor cursor, double size) {
		setAsDouble(cursor, iVolume, size);
	}

	@Deprecated
	public int getSizeDecimal(RecordCursor cursor) {
		return getAsTinyDecimal(cursor, iVolume);
	}

	@Deprecated
	public void setSizeDecimal(RecordCursor cursor, int size) {
		setAsTinyDecimal(cursor, iVolume, size);
	}

	@Deprecated
	public long getSizeWideDecimal(RecordCursor cursor) {
		return getAsWideDecimal(cursor, iVolume);
	}

	@Deprecated
	public void setSizeWideDecimal(RecordCursor cursor, long size) {
		setAsWideDecimal(cursor, iVolume, size);
	}

	public int getVolume(RecordCursor cursor) {
		return getAsInt(cursor, iVolume);
	}

	public void setVolume(RecordCursor cursor, int volume) {
		setAsInt(cursor, iVolume, volume);
	}

	public long getVolumeLong(RecordCursor cursor) {
		return getAsLong(cursor, iVolume);
	}

	public void setVolumeLong(RecordCursor cursor, long volume) {
		setAsLong(cursor, iVolume, volume);
	}

	public double getVolumeDouble(RecordCursor cursor) {
		return getAsDouble(cursor, iVolume);
	}

	public void setVolumeDouble(RecordCursor cursor, double volume) {
		setAsDouble(cursor, iVolume, volume);
	}

	public int getVolumeDecimal(RecordCursor cursor) {
		return getAsTinyDecimal(cursor, iVolume);
	}

	public void setVolumeDecimal(RecordCursor cursor, int volume) {
		setAsTinyDecimal(cursor, iVolume, volume);
	}

	public long getVolumeWideDecimal(RecordCursor cursor) {
		return getAsWideDecimal(cursor, iVolume);
	}

	public void setVolumeWideDecimal(RecordCursor cursor, long volume) {
		setAsWideDecimal(cursor, iVolume, volume);
	}

	@Deprecated
	public double getBid(RecordCursor cursor) {
		if (iBidPrice < 0)
			return Double.NaN;
		return getAsDouble(cursor, iBidPrice);
	}

	@Deprecated
	public void setBid(RecordCursor cursor, double bid) {
		if (iBidPrice < 0)
			return;
		setAsDouble(cursor, iBidPrice, bid);
	}

	@Deprecated
	public int getBidDecimal(RecordCursor cursor) {
		if (iBidPrice < 0)
			return 0;
		return getAsTinyDecimal(cursor, iBidPrice);
	}

	@Deprecated
	public void setBidDecimal(RecordCursor cursor, int bid) {
		if (iBidPrice < 0)
			return;
		setAsTinyDecimal(cursor, iBidPrice, bid);
	}

	@Deprecated
	public long getBidWideDecimal(RecordCursor cursor) {
		if (iBidPrice < 0)
			return 0;
		return getAsWideDecimal(cursor, iBidPrice);
	}

	@Deprecated
	public void setBidWideDecimal(RecordCursor cursor, long bid) {
		if (iBidPrice < 0)
			return;
		setAsWideDecimal(cursor, iBidPrice, bid);
	}

	public double getBidPrice(RecordCursor cursor) {
		if (iBidPrice < 0)
			return Double.NaN;
		return getAsDouble(cursor, iBidPrice);
	}

	public void setBidPrice(RecordCursor cursor, double bidPrice) {
		if (iBidPrice < 0)
			return;
		setAsDouble(cursor, iBidPrice, bidPrice);
	}

	public int getBidPriceDecimal(RecordCursor cursor) {
		if (iBidPrice < 0)
			return 0;
		return getAsTinyDecimal(cursor, iBidPrice);
	}

	public void setBidPriceDecimal(RecordCursor cursor, int bidPrice) {
		if (iBidPrice < 0)
			return;
		setAsTinyDecimal(cursor, iBidPrice, bidPrice);
	}

	public long getBidPriceWideDecimal(RecordCursor cursor) {
		if (iBidPrice < 0)
			return 0;
		return getAsWideDecimal(cursor, iBidPrice);
	}

	public void setBidPriceWideDecimal(RecordCursor cursor, long bidPrice) {
		if (iBidPrice < 0)
			return;
		setAsWideDecimal(cursor, iBidPrice, bidPrice);
	}

	@Deprecated
	public double getAsk(RecordCursor cursor) {
		if (iAskPrice < 0)
			return Double.NaN;
		return getAsDouble(cursor, iAskPrice);
	}

	@Deprecated
	public void setAsk(RecordCursor cursor, double ask) {
		if (iAskPrice < 0)
			return;
		setAsDouble(cursor, iAskPrice, ask);
	}

	@Deprecated
	public int getAskDecimal(RecordCursor cursor) {
		if (iAskPrice < 0)
			return 0;
		return getAsTinyDecimal(cursor, iAskPrice);
	}

	@Deprecated
	public void setAskDecimal(RecordCursor cursor, int ask) {
		if (iAskPrice < 0)
			return;
		setAsTinyDecimal(cursor, iAskPrice, ask);
	}

	@Deprecated
	public long getAskWideDecimal(RecordCursor cursor) {
		if (iAskPrice < 0)
			return 0;
		return getAsWideDecimal(cursor, iAskPrice);
	}

	@Deprecated
	public void setAskWideDecimal(RecordCursor cursor, long ask) {
		if (iAskPrice < 0)
			return;
		setAsWideDecimal(cursor, iAskPrice, ask);
	}

	public double getAskPrice(RecordCursor cursor) {
		if (iAskPrice < 0)
			return Double.NaN;
		return getAsDouble(cursor, iAskPrice);
	}

	public void setAskPrice(RecordCursor cursor, double askPrice) {
		if (iAskPrice < 0)
			return;
		setAsDouble(cursor, iAskPrice, askPrice);
	}

	public int getAskPriceDecimal(RecordCursor cursor) {
		if (iAskPrice < 0)
			return 0;
		return getAsTinyDecimal(cursor, iAskPrice);
	}

	public void setAskPriceDecimal(RecordCursor cursor, int askPrice) {
		if (iAskPrice < 0)
			return;
		setAsTinyDecimal(cursor, iAskPrice, askPrice);
	}

	public long getAskPriceWideDecimal(RecordCursor cursor) {
		if (iAskPrice < 0)
			return 0;
		return getAsWideDecimal(cursor, iAskPrice);
	}

	public void setAskPriceWideDecimal(RecordCursor cursor, long askPrice) {
		if (iAskPrice < 0)
			return;
		setAsWideDecimal(cursor, iAskPrice, askPrice);
	}
// END: CODE AUTOMATICALLY GENERATED
}
