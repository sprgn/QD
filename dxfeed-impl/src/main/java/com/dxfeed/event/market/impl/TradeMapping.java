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
package com.dxfeed.event.market.impl;

import com.devexperts.qd.DataRecord;
import com.devexperts.qd.ng.RecordCursor;
import com.devexperts.qd.util.Decimal;
import com.devexperts.qd.util.MappingUtil;
import com.devexperts.util.TimeUtil;

public class TradeMapping extends MarketEventMapping {
// BEGIN: CODE AUTOMATICALLY GENERATED: DO NOT MODIFY. IT IS REGENERATED BY com.dxfeed.api.codegen.ImplCodeGen
	private final int iTime;
	private final int iSequence;
	private final int iTimeNanoPart;
	private final int iExchangeCode;
	private final int iPrice;
	private final int iSize;
	private final int iTick;
	private final int iChange;
	private final int iFlags;
	private final int iDayVolume;
	private final int iDayTurnover;

	public TradeMapping(DataRecord record) {
		super(record);
		iTime = MappingUtil.findIntField(record, "Last.Time", false);
		iSequence = MappingUtil.findIntField(record, "Last.Sequence", false);
		iTimeNanoPart = MappingUtil.findIntField(record, "Last.TimeNanoPart", false);
		iExchangeCode = MappingUtil.findIntField(record, "Last.Exchange", false);
		iPrice = findIntField("Last.Price", true);
		iSize = findIntField("Last.Size", true);
		iTick = MappingUtil.findIntField(record, "Last.Tick", false);
		iChange = findIntField("Last.Change", false);
		iFlags = MappingUtil.findIntField(record, "Last.Flags", false);
		iDayVolume = findIntField("Volume", false);
		iDayTurnover = findIntField("DayTurnover", false);
		putNonDefaultPropertyName("Last.Time", "Time");
		putNonDefaultPropertyName("Last.Sequence", "Sequence");
		putNonDefaultPropertyName("Last.TimeNanoPart", "TimeNanoPart");
		putNonDefaultPropertyName("Last.Exchange", "ExchangeCode");
		putNonDefaultPropertyName("Last.Price", "Price");
		putNonDefaultPropertyName("Last.Size", "Size");
		putNonDefaultPropertyName("Last.Tick", "Tick");
		putNonDefaultPropertyName("Last.Change", "Change");
		putNonDefaultPropertyName("Last.Flags", "Flags");
		putNonDefaultPropertyName("Volume", "DayVolume");
	}

	@Deprecated
	public long getLastTimeMillis(RecordCursor cursor) {
		if (iTime < 0)
			return 0;
		return getInt(cursor, iTime) * 1000L;
	}

	@Deprecated
	public void setLastTimeMillis(RecordCursor cursor, long lastTime) {
		if (iTime < 0)
			return;
		setInt(cursor, iTime, TimeUtil.getSecondsFromTime(lastTime));
	}

	@Deprecated
	public int getLastTimeSeconds(RecordCursor cursor) {
		if (iTime < 0)
			return 0;
		return getInt(cursor, iTime);
	}

	@Deprecated
	public void setLastTimeSeconds(RecordCursor cursor, int lastTime) {
		if (iTime < 0)
			return;
		setInt(cursor, iTime, lastTime);
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

	@Deprecated
	public int getLastSequence(RecordCursor cursor) {
		if (iSequence < 0)
			return 0;
		return getInt(cursor, iSequence);
	}

	@Deprecated
	public void setLastSequence(RecordCursor cursor, int lastSequence) {
		if (iSequence < 0)
			return;
		setInt(cursor, iSequence, lastSequence);
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
	public int getLastTimeNanoPart(RecordCursor cursor) {
		if (iTimeNanoPart < 0)
			return 0;
		return getInt(cursor, iTimeNanoPart);
	}

	@Deprecated
	public void setLastTimeNanoPart(RecordCursor cursor, int lastTimeNanoPart) {
		if (iTimeNanoPart < 0)
			return;
		setInt(cursor, iTimeNanoPart, lastTimeNanoPart);
	}

	public int getTimeNanoPart(RecordCursor cursor) {
		if (iTimeNanoPart < 0)
			return 0;
		return getInt(cursor, iTimeNanoPart);
	}

	public void setTimeNanoPart(RecordCursor cursor, int timeNanoPart) {
		if (iTimeNanoPart < 0)
			return;
		setInt(cursor, iTimeNanoPart, timeNanoPart);
	}

	@Deprecated
	public char getLastExchange(RecordCursor cursor) {
		if (iExchangeCode < 0)
			return recordExchange;
		return (char)getInt(cursor, iExchangeCode);
	}

	@Deprecated
	public void setLastExchange(RecordCursor cursor, char lastExchange) {
		if (iExchangeCode < 0)
			return;
		setInt(cursor, iExchangeCode, lastExchange);
	}

	public char getExchangeCode(RecordCursor cursor) {
		if (iExchangeCode < 0)
			return recordExchange;
		return (char)getInt(cursor, iExchangeCode);
	}

	public void setExchangeCode(RecordCursor cursor, char exchangeCode) {
		if (iExchangeCode < 0)
			return;
		setInt(cursor, iExchangeCode, exchangeCode);
	}

	@Deprecated
	public double getLastPrice(RecordCursor cursor) {
		return getAsDouble(cursor, iPrice);
	}

	@Deprecated
	public void setLastPrice(RecordCursor cursor, double lastPrice) {
		setAsDouble(cursor, iPrice, lastPrice);
	}

	@Deprecated
	public int getLastPriceDecimal(RecordCursor cursor) {
		return getAsTinyDecimal(cursor, iPrice);
	}

	@Deprecated
	public void setLastPriceDecimal(RecordCursor cursor, int lastPrice) {
		setAsTinyDecimal(cursor, iPrice, lastPrice);
	}

	@Deprecated
	public long getLastPriceWideDecimal(RecordCursor cursor) {
		return getAsWideDecimal(cursor, iPrice);
	}

	@Deprecated
	public void setLastPriceWideDecimal(RecordCursor cursor, long lastPrice) {
		setAsWideDecimal(cursor, iPrice, lastPrice);
	}

	public double getPrice(RecordCursor cursor) {
		return getAsDouble(cursor, iPrice);
	}

	public void setPrice(RecordCursor cursor, double price) {
		setAsDouble(cursor, iPrice, price);
	}

	public int getPriceDecimal(RecordCursor cursor) {
		return getAsTinyDecimal(cursor, iPrice);
	}

	public void setPriceDecimal(RecordCursor cursor, int price) {
		setAsTinyDecimal(cursor, iPrice, price);
	}

	public long getPriceWideDecimal(RecordCursor cursor) {
		return getAsWideDecimal(cursor, iPrice);
	}

	public void setPriceWideDecimal(RecordCursor cursor, long price) {
		setAsWideDecimal(cursor, iPrice, price);
	}

	@Deprecated
	public int getLastSize(RecordCursor cursor) {
		return getAsInt(cursor, iSize);
	}

	@Deprecated
	public void setLastSize(RecordCursor cursor, int lastSize) {
		setAsInt(cursor, iSize, lastSize);
	}

	@Deprecated
	public long getLastSizeLong(RecordCursor cursor) {
		return getAsLong(cursor, iSize);
	}

	@Deprecated
	public void setLastSizeLong(RecordCursor cursor, long lastSize) {
		setAsLong(cursor, iSize, lastSize);
	}

	@Deprecated
	public double getLastSizeDouble(RecordCursor cursor) {
		return getAsDouble(cursor, iSize);
	}

	@Deprecated
	public void setLastSizeDouble(RecordCursor cursor, double lastSize) {
		setAsDouble(cursor, iSize, lastSize);
	}

	@Deprecated
	public int getLastSizeDecimal(RecordCursor cursor) {
		return getAsTinyDecimal(cursor, iSize);
	}

	@Deprecated
	public void setLastSizeDecimal(RecordCursor cursor, int lastSize) {
		setAsTinyDecimal(cursor, iSize, lastSize);
	}

	@Deprecated
	public long getLastSizeWideDecimal(RecordCursor cursor) {
		return getAsWideDecimal(cursor, iSize);
	}

	@Deprecated
	public void setLastSizeWideDecimal(RecordCursor cursor, long lastSize) {
		setAsWideDecimal(cursor, iSize, lastSize);
	}

	public int getSize(RecordCursor cursor) {
		return getAsInt(cursor, iSize);
	}

	public void setSize(RecordCursor cursor, int size) {
		setAsInt(cursor, iSize, size);
	}

	public long getSizeLong(RecordCursor cursor) {
		return getAsLong(cursor, iSize);
	}

	public void setSizeLong(RecordCursor cursor, long size) {
		setAsLong(cursor, iSize, size);
	}

	public double getSizeDouble(RecordCursor cursor) {
		return getAsDouble(cursor, iSize);
	}

	public void setSizeDouble(RecordCursor cursor, double size) {
		setAsDouble(cursor, iSize, size);
	}

	public int getSizeDecimal(RecordCursor cursor) {
		return getAsTinyDecimal(cursor, iSize);
	}

	public void setSizeDecimal(RecordCursor cursor, int size) {
		setAsTinyDecimal(cursor, iSize, size);
	}

	public long getSizeWideDecimal(RecordCursor cursor) {
		return getAsWideDecimal(cursor, iSize);
	}

	public void setSizeWideDecimal(RecordCursor cursor, long size) {
		setAsWideDecimal(cursor, iSize, size);
	}

	@Deprecated
	public int getLastTick(RecordCursor cursor) {
		if (iTick < 0)
			return 0;
		return getInt(cursor, iTick);
	}

	@Deprecated
	public void setLastTick(RecordCursor cursor, int lastTick) {
		if (iTick < 0)
			return;
		setInt(cursor, iTick, lastTick);
	}

	public int getTick(RecordCursor cursor) {
		if (iTick < 0)
			return 0;
		return getInt(cursor, iTick);
	}

	public void setTick(RecordCursor cursor, int tick) {
		if (iTick < 0)
			return;
		setInt(cursor, iTick, tick);
	}

	@Deprecated
	public double getLastChange(RecordCursor cursor) {
		if (iChange < 0)
			return Double.NaN;
		return getAsDouble(cursor, iChange);
	}

	@Deprecated
	public void setLastChange(RecordCursor cursor, double lastChange) {
		if (iChange < 0)
			return;
		setAsDouble(cursor, iChange, lastChange);
	}

	@Deprecated
	public int getLastChangeDecimal(RecordCursor cursor) {
		if (iChange < 0)
			return 0;
		return getAsTinyDecimal(cursor, iChange);
	}

	@Deprecated
	public void setLastChangeDecimal(RecordCursor cursor, int lastChange) {
		if (iChange < 0)
			return;
		setAsTinyDecimal(cursor, iChange, lastChange);
	}

	@Deprecated
	public long getLastChangeWideDecimal(RecordCursor cursor) {
		if (iChange < 0)
			return 0;
		return getAsWideDecimal(cursor, iChange);
	}

	@Deprecated
	public void setLastChangeWideDecimal(RecordCursor cursor, long lastChange) {
		if (iChange < 0)
			return;
		setAsWideDecimal(cursor, iChange, lastChange);
	}

	public double getChange(RecordCursor cursor) {
		if (iChange < 0)
			return Double.NaN;
		return getAsDouble(cursor, iChange);
	}

	public void setChange(RecordCursor cursor, double change) {
		if (iChange < 0)
			return;
		setAsDouble(cursor, iChange, change);
	}

	public int getChangeDecimal(RecordCursor cursor) {
		if (iChange < 0)
			return 0;
		return getAsTinyDecimal(cursor, iChange);
	}

	public void setChangeDecimal(RecordCursor cursor, int change) {
		if (iChange < 0)
			return;
		setAsTinyDecimal(cursor, iChange, change);
	}

	public long getChangeWideDecimal(RecordCursor cursor) {
		if (iChange < 0)
			return 0;
		return getAsWideDecimal(cursor, iChange);
	}

	public void setChangeWideDecimal(RecordCursor cursor, long change) {
		if (iChange < 0)
			return;
		setAsWideDecimal(cursor, iChange, change);
	}

	@Deprecated
	public int getLastFlags(RecordCursor cursor) {
		if (iFlags < 0)
			return 0;
		return getInt(cursor, iFlags);
	}

	@Deprecated
	public void setLastFlags(RecordCursor cursor, int lastFlags) {
		if (iFlags < 0)
			return;
		setInt(cursor, iFlags, lastFlags);
	}

	public int getFlags(RecordCursor cursor) {
		if (iFlags < 0)
			return 0;
		return getInt(cursor, iFlags);
	}

	public void setFlags(RecordCursor cursor, int flags) {
		if (iFlags < 0)
			return;
		setInt(cursor, iFlags, flags);
	}

	@Deprecated
	public long getVolume(RecordCursor cursor) {
		if (iDayVolume < 0)
			return 0;
		return getAsLong(cursor, iDayVolume);
	}

	@Deprecated
	public void setVolume(RecordCursor cursor, long volume) {
		if (iDayVolume < 0)
			return;
		setAsLong(cursor, iDayVolume, volume);
	}

	@Deprecated
	public double getVolumeDouble(RecordCursor cursor) {
		if (iDayVolume < 0)
			return Double.NaN;
		return getAsDouble(cursor, iDayVolume);
	}

	@Deprecated
	public void setVolumeDouble(RecordCursor cursor, double volume) {
		if (iDayVolume < 0)
			return;
		setAsDouble(cursor, iDayVolume, volume);
	}

	@Deprecated
	public int getVolumeDecimal(RecordCursor cursor) {
		if (iDayVolume < 0)
			return 0;
		return getAsTinyDecimal(cursor, iDayVolume);
	}

	@Deprecated
	public void setVolumeDecimal(RecordCursor cursor, int volume) {
		if (iDayVolume < 0)
			return;
		setAsTinyDecimal(cursor, iDayVolume, volume);
	}

	@Deprecated
	public long getVolumeWideDecimal(RecordCursor cursor) {
		if (iDayVolume < 0)
			return 0;
		return getAsWideDecimal(cursor, iDayVolume);
	}

	@Deprecated
	public void setVolumeWideDecimal(RecordCursor cursor, long volume) {
		if (iDayVolume < 0)
			return;
		setAsWideDecimal(cursor, iDayVolume, volume);
	}

	public long getDayVolume(RecordCursor cursor) {
		if (iDayVolume < 0)
			return 0;
		return getAsLong(cursor, iDayVolume);
	}

	public void setDayVolume(RecordCursor cursor, long dayVolume) {
		if (iDayVolume < 0)
			return;
		setAsLong(cursor, iDayVolume, dayVolume);
	}

	public double getDayVolumeDouble(RecordCursor cursor) {
		if (iDayVolume < 0)
			return Double.NaN;
		return getAsDouble(cursor, iDayVolume);
	}

	public void setDayVolumeDouble(RecordCursor cursor, double dayVolume) {
		if (iDayVolume < 0)
			return;
		setAsDouble(cursor, iDayVolume, dayVolume);
	}

	public int getDayVolumeDecimal(RecordCursor cursor) {
		if (iDayVolume < 0)
			return 0;
		return getAsTinyDecimal(cursor, iDayVolume);
	}

	public void setDayVolumeDecimal(RecordCursor cursor, int dayVolume) {
		if (iDayVolume < 0)
			return;
		setAsTinyDecimal(cursor, iDayVolume, dayVolume);
	}

	public long getDayVolumeWideDecimal(RecordCursor cursor) {
		if (iDayVolume < 0)
			return 0;
		return getAsWideDecimal(cursor, iDayVolume);
	}

	public void setDayVolumeWideDecimal(RecordCursor cursor, long dayVolume) {
		if (iDayVolume < 0)
			return;
		setAsWideDecimal(cursor, iDayVolume, dayVolume);
	}

	public double getDayTurnover(RecordCursor cursor) {
		if (iDayTurnover < 0)
			return Double.NaN;
		return getAsDouble(cursor, iDayTurnover);
	}

	public void setDayTurnover(RecordCursor cursor, double dayTurnover) {
		if (iDayTurnover < 0)
			return;
		setAsDouble(cursor, iDayTurnover, dayTurnover);
	}

	public int getDayTurnoverDecimal(RecordCursor cursor) {
		if (iDayTurnover < 0)
			return 0;
		return getAsTinyDecimal(cursor, iDayTurnover);
	}

	public void setDayTurnoverDecimal(RecordCursor cursor, int dayTurnover) {
		if (iDayTurnover < 0)
			return;
		setAsTinyDecimal(cursor, iDayTurnover, dayTurnover);
	}

	public long getDayTurnoverWideDecimal(RecordCursor cursor) {
		if (iDayTurnover < 0)
			return 0;
		return getAsWideDecimal(cursor, iDayTurnover);
	}

	public void setDayTurnoverWideDecimal(RecordCursor cursor, long dayTurnover) {
		if (iDayTurnover < 0)
			return;
		setAsWideDecimal(cursor, iDayTurnover, dayTurnover);
	}
// END: CODE AUTOMATICALLY GENERATED
}
