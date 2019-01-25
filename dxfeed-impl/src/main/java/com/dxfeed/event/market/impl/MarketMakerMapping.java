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
package com.dxfeed.event.market.impl;

import com.devexperts.qd.DataRecord;
import com.devexperts.qd.ng.RecordCursor;
import com.devexperts.qd.util.*;
import com.devexperts.util.TimeUtil;

public class MarketMakerMapping extends MarketEventMapping {
// BEGIN: CODE AUTOMATICALLY GENERATED: DO NOT MODIFY. IT IS REGENERATED BY com.dxfeed.api.codegen.ImplCodeGen
	private final int iExchangeCode;
	private final int iMarketMaker;
	private final int iBidTime;
	private final int iBidPrice;
	private final int iBidSize;
	private final int iBidCount;
	private final int iAskTime;
	private final int iAskPrice;
	private final int iAskSize;
	private final int iAskCount;

	public MarketMakerMapping(DataRecord record) {
		super(record);
		iExchangeCode = MappingUtil.findIntField(record, "MMExchange", true);
		iMarketMaker = MappingUtil.findIntField(record, "MMID", true);
		iBidTime = MappingUtil.findIntField(record, "MMBid.Time", false);
		iBidPrice = findIntField("MMBid.Price", true);
		iBidSize = findIntField("MMBid.Size", true);
		iBidCount = findIntField("MMBid.Count", false);
		iAskTime = MappingUtil.findIntField(record, "MMAsk.Time", false);
		iAskPrice = findIntField("MMAsk.Price", true);
		iAskSize = findIntField("MMAsk.Size", true);
		iAskCount = findIntField("MMAsk.Count", false);
		putNonDefaultPropertyName("MMExchange", "ExchangeCode");
		putNonDefaultPropertyName("MMID", "MarketMaker");
		putNonDefaultPropertyName("MMBid.Time", "BidTime");
		putNonDefaultPropertyName("MMBid.Price", "BidPrice");
		putNonDefaultPropertyName("MMBid.Size", "BidSize");
		putNonDefaultPropertyName("MMBid.Count", "BidCount");
		putNonDefaultPropertyName("MMAsk.Time", "AskTime");
		putNonDefaultPropertyName("MMAsk.Price", "AskPrice");
		putNonDefaultPropertyName("MMAsk.Size", "AskSize");
		putNonDefaultPropertyName("MMAsk.Count", "AskCount");
	}

	@Deprecated
	public char getMMExchange(RecordCursor cursor) {
		return (char)getInt(cursor, iExchangeCode);
	}

	@Deprecated
	public void setMMExchange(RecordCursor cursor, char _MMExchange) {
		setInt(cursor, iExchangeCode, _MMExchange);
	}

	public char getExchangeCode(RecordCursor cursor) {
		return (char)getInt(cursor, iExchangeCode);
	}

	public void setExchangeCode(RecordCursor cursor, char exchangeCode) {
		setInt(cursor, iExchangeCode, exchangeCode);
	}

	@Deprecated
	public String getMMIDString(RecordCursor cursor) {
		return ShortString.decode(getInt(cursor, iMarketMaker));
	}

	@Deprecated
	public void setMMIDString(RecordCursor cursor, String _MMID) {
		setInt(cursor, iMarketMaker, (int)ShortString.encode(_MMID));
	}

	@Deprecated
	public int getMMID(RecordCursor cursor) {
		return getInt(cursor, iMarketMaker);
	}

	@Deprecated
	public void setMMID(RecordCursor cursor, int _MMID) {
		setInt(cursor, iMarketMaker, _MMID);
	}

	public String getMarketMakerString(RecordCursor cursor) {
		return ShortString.decode(getInt(cursor, iMarketMaker));
	}

	public void setMarketMakerString(RecordCursor cursor, String marketMaker) {
		setInt(cursor, iMarketMaker, (int)ShortString.encode(marketMaker));
	}

	public int getMarketMaker(RecordCursor cursor) {
		return getInt(cursor, iMarketMaker);
	}

	public void setMarketMaker(RecordCursor cursor, int marketMaker) {
		setInt(cursor, iMarketMaker, marketMaker);
	}

	@Deprecated
	public long getMMBidTimeMillis(RecordCursor cursor) {
		if (iBidTime < 0)
			return 0;
		return getInt(cursor, iBidTime) * 1000L;
	}

	@Deprecated
	public void setMMBidTimeMillis(RecordCursor cursor, long _MMBidTime) {
		if (iBidTime < 0)
			return;
		setInt(cursor, iBidTime, TimeUtil.getSecondsFromTime(_MMBidTime));
	}

	@Deprecated
	public int getMMBidTimeSeconds(RecordCursor cursor) {
		if (iBidTime < 0)
			return 0;
		return getInt(cursor, iBidTime);
	}

	@Deprecated
	public void setMMBidTimeSeconds(RecordCursor cursor, int _MMBidTime) {
		if (iBidTime < 0)
			return;
		setInt(cursor, iBidTime, _MMBidTime);
	}

	public long getBidTimeMillis(RecordCursor cursor) {
		if (iBidTime < 0)
			return 0;
		return getInt(cursor, iBidTime) * 1000L;
	}

	public void setBidTimeMillis(RecordCursor cursor, long bidTime) {
		if (iBidTime < 0)
			return;
		setInt(cursor, iBidTime, TimeUtil.getSecondsFromTime(bidTime));
	}

	public int getBidTimeSeconds(RecordCursor cursor) {
		if (iBidTime < 0)
			return 0;
		return getInt(cursor, iBidTime);
	}

	public void setBidTimeSeconds(RecordCursor cursor, int bidTime) {
		if (iBidTime < 0)
			return;
		setInt(cursor, iBidTime, bidTime);
	}

	@Deprecated
	public double getMMBidPrice(RecordCursor cursor) {
		return getAsDouble(cursor, iBidPrice);
	}

	@Deprecated
	public void setMMBidPrice(RecordCursor cursor, double _MMBidPrice) {
		setAsDouble(cursor, iBidPrice, _MMBidPrice);
	}

	@Deprecated
	public int getMMBidPriceDecimal(RecordCursor cursor) {
		return getAsTinyDecimal(cursor, iBidPrice);
	}

	@Deprecated
	public void setMMBidPriceDecimal(RecordCursor cursor, int _MMBidPrice) {
		setAsTinyDecimal(cursor, iBidPrice, _MMBidPrice);
	}

	@Deprecated
	public long getMMBidPriceWideDecimal(RecordCursor cursor) {
		return getAsWideDecimal(cursor, iBidPrice);
	}

	@Deprecated
	public void setMMBidPriceWideDecimal(RecordCursor cursor, long _MMBidPrice) {
		setAsWideDecimal(cursor, iBidPrice, _MMBidPrice);
	}

	public double getBidPrice(RecordCursor cursor) {
		return getAsDouble(cursor, iBidPrice);
	}

	public void setBidPrice(RecordCursor cursor, double bidPrice) {
		setAsDouble(cursor, iBidPrice, bidPrice);
	}

	public int getBidPriceDecimal(RecordCursor cursor) {
		return getAsTinyDecimal(cursor, iBidPrice);
	}

	public void setBidPriceDecimal(RecordCursor cursor, int bidPrice) {
		setAsTinyDecimal(cursor, iBidPrice, bidPrice);
	}

	public long getBidPriceWideDecimal(RecordCursor cursor) {
		return getAsWideDecimal(cursor, iBidPrice);
	}

	public void setBidPriceWideDecimal(RecordCursor cursor, long bidPrice) {
		setAsWideDecimal(cursor, iBidPrice, bidPrice);
	}

	@Deprecated
	public int getMMBidSize(RecordCursor cursor) {
		return getAsInt(cursor, iBidSize);
	}

	@Deprecated
	public void setMMBidSize(RecordCursor cursor, int _MMBidSize) {
		setAsInt(cursor, iBidSize, _MMBidSize);
	}

	@Deprecated
	public long getMMBidSizeLong(RecordCursor cursor) {
		return getAsLong(cursor, iBidSize);
	}

	@Deprecated
	public void setMMBidSizeLong(RecordCursor cursor, long _MMBidSize) {
		setAsLong(cursor, iBidSize, _MMBidSize);
	}

	@Deprecated
	public double getMMBidSizeDouble(RecordCursor cursor) {
		return getAsDouble(cursor, iBidSize);
	}

	@Deprecated
	public void setMMBidSizeDouble(RecordCursor cursor, double _MMBidSize) {
		setAsDouble(cursor, iBidSize, _MMBidSize);
	}

	@Deprecated
	public int getMMBidSizeDecimal(RecordCursor cursor) {
		return getAsTinyDecimal(cursor, iBidSize);
	}

	@Deprecated
	public void setMMBidSizeDecimal(RecordCursor cursor, int _MMBidSize) {
		setAsTinyDecimal(cursor, iBidSize, _MMBidSize);
	}

	@Deprecated
	public long getMMBidSizeWideDecimal(RecordCursor cursor) {
		return getAsWideDecimal(cursor, iBidSize);
	}

	@Deprecated
	public void setMMBidSizeWideDecimal(RecordCursor cursor, long _MMBidSize) {
		setAsWideDecimal(cursor, iBidSize, _MMBidSize);
	}

	public int getBidSize(RecordCursor cursor) {
		return getAsInt(cursor, iBidSize);
	}

	public void setBidSize(RecordCursor cursor, int bidSize) {
		setAsInt(cursor, iBidSize, bidSize);
	}

	public long getBidSizeLong(RecordCursor cursor) {
		return getAsLong(cursor, iBidSize);
	}

	public void setBidSizeLong(RecordCursor cursor, long bidSize) {
		setAsLong(cursor, iBidSize, bidSize);
	}

	public double getBidSizeDouble(RecordCursor cursor) {
		return getAsDouble(cursor, iBidSize);
	}

	public void setBidSizeDouble(RecordCursor cursor, double bidSize) {
		setAsDouble(cursor, iBidSize, bidSize);
	}

	public int getBidSizeDecimal(RecordCursor cursor) {
		return getAsTinyDecimal(cursor, iBidSize);
	}

	public void setBidSizeDecimal(RecordCursor cursor, int bidSize) {
		setAsTinyDecimal(cursor, iBidSize, bidSize);
	}

	public long getBidSizeWideDecimal(RecordCursor cursor) {
		return getAsWideDecimal(cursor, iBidSize);
	}

	public void setBidSizeWideDecimal(RecordCursor cursor, long bidSize) {
		setAsWideDecimal(cursor, iBidSize, bidSize);
	}

	@Deprecated
	public int getMMBidCount(RecordCursor cursor) {
		if (iBidCount < 0)
			return 0;
		return getAsInt(cursor, iBidCount);
	}

	@Deprecated
	public void setMMBidCount(RecordCursor cursor, int _MMBidCount) {
		if (iBidCount < 0)
			return;
		setAsInt(cursor, iBidCount, _MMBidCount);
	}

	@Deprecated
	public long getMMBidCountLong(RecordCursor cursor) {
		if (iBidCount < 0)
			return 0;
		return getAsLong(cursor, iBidCount);
	}

	@Deprecated
	public void setMMBidCountLong(RecordCursor cursor, long _MMBidCount) {
		if (iBidCount < 0)
			return;
		setAsLong(cursor, iBidCount, _MMBidCount);
	}

	@Deprecated
	public double getMMBidCountDouble(RecordCursor cursor) {
		if (iBidCount < 0)
			return Double.NaN;
		return getAsDouble(cursor, iBidCount);
	}

	@Deprecated
	public void setMMBidCountDouble(RecordCursor cursor, double _MMBidCount) {
		if (iBidCount < 0)
			return;
		setAsDouble(cursor, iBidCount, _MMBidCount);
	}

	@Deprecated
	public int getMMBidCountDecimal(RecordCursor cursor) {
		if (iBidCount < 0)
			return 0;
		return getAsTinyDecimal(cursor, iBidCount);
	}

	@Deprecated
	public void setMMBidCountDecimal(RecordCursor cursor, int _MMBidCount) {
		if (iBidCount < 0)
			return;
		setAsTinyDecimal(cursor, iBidCount, _MMBidCount);
	}

	@Deprecated
	public long getMMBidCountWideDecimal(RecordCursor cursor) {
		if (iBidCount < 0)
			return 0;
		return getAsWideDecimal(cursor, iBidCount);
	}

	@Deprecated
	public void setMMBidCountWideDecimal(RecordCursor cursor, long _MMBidCount) {
		if (iBidCount < 0)
			return;
		setAsWideDecimal(cursor, iBidCount, _MMBidCount);
	}

	public int getBidCount(RecordCursor cursor) {
		if (iBidCount < 0)
			return 0;
		return getAsInt(cursor, iBidCount);
	}

	public void setBidCount(RecordCursor cursor, int bidCount) {
		if (iBidCount < 0)
			return;
		setAsInt(cursor, iBidCount, bidCount);
	}

	public long getBidCountLong(RecordCursor cursor) {
		if (iBidCount < 0)
			return 0;
		return getAsLong(cursor, iBidCount);
	}

	public void setBidCountLong(RecordCursor cursor, long bidCount) {
		if (iBidCount < 0)
			return;
		setAsLong(cursor, iBidCount, bidCount);
	}

	public double getBidCountDouble(RecordCursor cursor) {
		if (iBidCount < 0)
			return Double.NaN;
		return getAsDouble(cursor, iBidCount);
	}

	public void setBidCountDouble(RecordCursor cursor, double bidCount) {
		if (iBidCount < 0)
			return;
		setAsDouble(cursor, iBidCount, bidCount);
	}

	public int getBidCountDecimal(RecordCursor cursor) {
		if (iBidCount < 0)
			return 0;
		return getAsTinyDecimal(cursor, iBidCount);
	}

	public void setBidCountDecimal(RecordCursor cursor, int bidCount) {
		if (iBidCount < 0)
			return;
		setAsTinyDecimal(cursor, iBidCount, bidCount);
	}

	public long getBidCountWideDecimal(RecordCursor cursor) {
		if (iBidCount < 0)
			return 0;
		return getAsWideDecimal(cursor, iBidCount);
	}

	public void setBidCountWideDecimal(RecordCursor cursor, long bidCount) {
		if (iBidCount < 0)
			return;
		setAsWideDecimal(cursor, iBidCount, bidCount);
	}

	@Deprecated
	public long getMMAskTimeMillis(RecordCursor cursor) {
		if (iAskTime < 0)
			return 0;
		return getInt(cursor, iAskTime) * 1000L;
	}

	@Deprecated
	public void setMMAskTimeMillis(RecordCursor cursor, long _MMAskTime) {
		if (iAskTime < 0)
			return;
		setInt(cursor, iAskTime, TimeUtil.getSecondsFromTime(_MMAskTime));
	}

	@Deprecated
	public int getMMAskTimeSeconds(RecordCursor cursor) {
		if (iAskTime < 0)
			return 0;
		return getInt(cursor, iAskTime);
	}

	@Deprecated
	public void setMMAskTimeSeconds(RecordCursor cursor, int _MMAskTime) {
		if (iAskTime < 0)
			return;
		setInt(cursor, iAskTime, _MMAskTime);
	}

	public long getAskTimeMillis(RecordCursor cursor) {
		if (iAskTime < 0)
			return 0;
		return getInt(cursor, iAskTime) * 1000L;
	}

	public void setAskTimeMillis(RecordCursor cursor, long askTime) {
		if (iAskTime < 0)
			return;
		setInt(cursor, iAskTime, TimeUtil.getSecondsFromTime(askTime));
	}

	public int getAskTimeSeconds(RecordCursor cursor) {
		if (iAskTime < 0)
			return 0;
		return getInt(cursor, iAskTime);
	}

	public void setAskTimeSeconds(RecordCursor cursor, int askTime) {
		if (iAskTime < 0)
			return;
		setInt(cursor, iAskTime, askTime);
	}

	@Deprecated
	public double getMMAskPrice(RecordCursor cursor) {
		return getAsDouble(cursor, iAskPrice);
	}

	@Deprecated
	public void setMMAskPrice(RecordCursor cursor, double _MMAskPrice) {
		setAsDouble(cursor, iAskPrice, _MMAskPrice);
	}

	@Deprecated
	public int getMMAskPriceDecimal(RecordCursor cursor) {
		return getAsTinyDecimal(cursor, iAskPrice);
	}

	@Deprecated
	public void setMMAskPriceDecimal(RecordCursor cursor, int _MMAskPrice) {
		setAsTinyDecimal(cursor, iAskPrice, _MMAskPrice);
	}

	@Deprecated
	public long getMMAskPriceWideDecimal(RecordCursor cursor) {
		return getAsWideDecimal(cursor, iAskPrice);
	}

	@Deprecated
	public void setMMAskPriceWideDecimal(RecordCursor cursor, long _MMAskPrice) {
		setAsWideDecimal(cursor, iAskPrice, _MMAskPrice);
	}

	public double getAskPrice(RecordCursor cursor) {
		return getAsDouble(cursor, iAskPrice);
	}

	public void setAskPrice(RecordCursor cursor, double askPrice) {
		setAsDouble(cursor, iAskPrice, askPrice);
	}

	public int getAskPriceDecimal(RecordCursor cursor) {
		return getAsTinyDecimal(cursor, iAskPrice);
	}

	public void setAskPriceDecimal(RecordCursor cursor, int askPrice) {
		setAsTinyDecimal(cursor, iAskPrice, askPrice);
	}

	public long getAskPriceWideDecimal(RecordCursor cursor) {
		return getAsWideDecimal(cursor, iAskPrice);
	}

	public void setAskPriceWideDecimal(RecordCursor cursor, long askPrice) {
		setAsWideDecimal(cursor, iAskPrice, askPrice);
	}

	@Deprecated
	public int getMMAskSize(RecordCursor cursor) {
		return getAsInt(cursor, iAskSize);
	}

	@Deprecated
	public void setMMAskSize(RecordCursor cursor, int _MMAskSize) {
		setAsInt(cursor, iAskSize, _MMAskSize);
	}

	@Deprecated
	public long getMMAskSizeLong(RecordCursor cursor) {
		return getAsLong(cursor, iAskSize);
	}

	@Deprecated
	public void setMMAskSizeLong(RecordCursor cursor, long _MMAskSize) {
		setAsLong(cursor, iAskSize, _MMAskSize);
	}

	@Deprecated
	public double getMMAskSizeDouble(RecordCursor cursor) {
		return getAsDouble(cursor, iAskSize);
	}

	@Deprecated
	public void setMMAskSizeDouble(RecordCursor cursor, double _MMAskSize) {
		setAsDouble(cursor, iAskSize, _MMAskSize);
	}

	@Deprecated
	public int getMMAskSizeDecimal(RecordCursor cursor) {
		return getAsTinyDecimal(cursor, iAskSize);
	}

	@Deprecated
	public void setMMAskSizeDecimal(RecordCursor cursor, int _MMAskSize) {
		setAsTinyDecimal(cursor, iAskSize, _MMAskSize);
	}

	@Deprecated
	public long getMMAskSizeWideDecimal(RecordCursor cursor) {
		return getAsWideDecimal(cursor, iAskSize);
	}

	@Deprecated
	public void setMMAskSizeWideDecimal(RecordCursor cursor, long _MMAskSize) {
		setAsWideDecimal(cursor, iAskSize, _MMAskSize);
	}

	public int getAskSize(RecordCursor cursor) {
		return getAsInt(cursor, iAskSize);
	}

	public void setAskSize(RecordCursor cursor, int askSize) {
		setAsInt(cursor, iAskSize, askSize);
	}

	public long getAskSizeLong(RecordCursor cursor) {
		return getAsLong(cursor, iAskSize);
	}

	public void setAskSizeLong(RecordCursor cursor, long askSize) {
		setAsLong(cursor, iAskSize, askSize);
	}

	public double getAskSizeDouble(RecordCursor cursor) {
		return getAsDouble(cursor, iAskSize);
	}

	public void setAskSizeDouble(RecordCursor cursor, double askSize) {
		setAsDouble(cursor, iAskSize, askSize);
	}

	public int getAskSizeDecimal(RecordCursor cursor) {
		return getAsTinyDecimal(cursor, iAskSize);
	}

	public void setAskSizeDecimal(RecordCursor cursor, int askSize) {
		setAsTinyDecimal(cursor, iAskSize, askSize);
	}

	public long getAskSizeWideDecimal(RecordCursor cursor) {
		return getAsWideDecimal(cursor, iAskSize);
	}

	public void setAskSizeWideDecimal(RecordCursor cursor, long askSize) {
		setAsWideDecimal(cursor, iAskSize, askSize);
	}

	@Deprecated
	public int getMMAskCount(RecordCursor cursor) {
		if (iAskCount < 0)
			return 0;
		return getAsInt(cursor, iAskCount);
	}

	@Deprecated
	public void setMMAskCount(RecordCursor cursor, int _MMAskCount) {
		if (iAskCount < 0)
			return;
		setAsInt(cursor, iAskCount, _MMAskCount);
	}

	@Deprecated
	public long getMMAskCountLong(RecordCursor cursor) {
		if (iAskCount < 0)
			return 0;
		return getAsLong(cursor, iAskCount);
	}

	@Deprecated
	public void setMMAskCountLong(RecordCursor cursor, long _MMAskCount) {
		if (iAskCount < 0)
			return;
		setAsLong(cursor, iAskCount, _MMAskCount);
	}

	@Deprecated
	public double getMMAskCountDouble(RecordCursor cursor) {
		if (iAskCount < 0)
			return Double.NaN;
		return getAsDouble(cursor, iAskCount);
	}

	@Deprecated
	public void setMMAskCountDouble(RecordCursor cursor, double _MMAskCount) {
		if (iAskCount < 0)
			return;
		setAsDouble(cursor, iAskCount, _MMAskCount);
	}

	@Deprecated
	public int getMMAskCountDecimal(RecordCursor cursor) {
		if (iAskCount < 0)
			return 0;
		return getAsTinyDecimal(cursor, iAskCount);
	}

	@Deprecated
	public void setMMAskCountDecimal(RecordCursor cursor, int _MMAskCount) {
		if (iAskCount < 0)
			return;
		setAsTinyDecimal(cursor, iAskCount, _MMAskCount);
	}

	@Deprecated
	public long getMMAskCountWideDecimal(RecordCursor cursor) {
		if (iAskCount < 0)
			return 0;
		return getAsWideDecimal(cursor, iAskCount);
	}

	@Deprecated
	public void setMMAskCountWideDecimal(RecordCursor cursor, long _MMAskCount) {
		if (iAskCount < 0)
			return;
		setAsWideDecimal(cursor, iAskCount, _MMAskCount);
	}

	public int getAskCount(RecordCursor cursor) {
		if (iAskCount < 0)
			return 0;
		return getAsInt(cursor, iAskCount);
	}

	public void setAskCount(RecordCursor cursor, int askCount) {
		if (iAskCount < 0)
			return;
		setAsInt(cursor, iAskCount, askCount);
	}

	public long getAskCountLong(RecordCursor cursor) {
		if (iAskCount < 0)
			return 0;
		return getAsLong(cursor, iAskCount);
	}

	public void setAskCountLong(RecordCursor cursor, long askCount) {
		if (iAskCount < 0)
			return;
		setAsLong(cursor, iAskCount, askCount);
	}

	public double getAskCountDouble(RecordCursor cursor) {
		if (iAskCount < 0)
			return Double.NaN;
		return getAsDouble(cursor, iAskCount);
	}

	public void setAskCountDouble(RecordCursor cursor, double askCount) {
		if (iAskCount < 0)
			return;
		setAsDouble(cursor, iAskCount, askCount);
	}

	public int getAskCountDecimal(RecordCursor cursor) {
		if (iAskCount < 0)
			return 0;
		return getAsTinyDecimal(cursor, iAskCount);
	}

	public void setAskCountDecimal(RecordCursor cursor, int askCount) {
		if (iAskCount < 0)
			return;
		setAsTinyDecimal(cursor, iAskCount, askCount);
	}

	public long getAskCountWideDecimal(RecordCursor cursor) {
		if (iAskCount < 0)
			return 0;
		return getAsWideDecimal(cursor, iAskCount);
	}

	public void setAskCountWideDecimal(RecordCursor cursor, long askCount) {
		if (iAskCount < 0)
			return;
		setAsWideDecimal(cursor, iAskCount, askCount);
	}
// END: CODE AUTOMATICALLY GENERATED
}
