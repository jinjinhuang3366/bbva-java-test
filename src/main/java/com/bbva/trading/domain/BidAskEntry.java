package com.bbva.trading.domain;

import java.io.Serializable;
import java.math.BigDecimal;

public class BidAskEntry implements Serializable {
    private BigDecimal bidPrice;
    private BigDecimal bidVolume;
    private BigDecimal askPrice;
    private BigDecimal askVolume;

    public BigDecimal getBidPrice() {
        return bidPrice;
    }

    public void setBidPrice(BigDecimal bidPrice) {
        this.bidPrice = bidPrice;
    }

    public BigDecimal getBidVolume() {
        return bidVolume;
    }

    public void setBidVolume(BigDecimal bidVolume) {
        this.bidVolume = bidVolume;
    }

    public BigDecimal getAskPrice() {
        return askPrice;
    }

    public void setAskPrice(BigDecimal askPrice) {
        this.askPrice = askPrice;
    }

    public BigDecimal getAskVolume() {
        return askVolume;
    }

    public void setAskVolume(BigDecimal askVolume) {
        this.askVolume = askVolume;
    }

    @Override
    public String toString() {
        return "BidAskEntry{" +
                "bidPrice=" + bidPrice +
                ", bidVolume=" + bidVolume +
                ", askPrice=" + askPrice +
                ", askVolume=" + askVolume +
                '}';
    }

    public static final class BidAskEntryBuilder {
        private BigDecimal bidPrice;
        private BigDecimal bidVolume;
        private BigDecimal askPrice;
        private BigDecimal askVolume;

        private BidAskEntryBuilder() {
        }

        public static BidAskEntryBuilder aBidAskEntry() {
            return new BidAskEntryBuilder();
        }

        public BidAskEntryBuilder withBidPrice(BigDecimal bidPrice) {
            this.bidPrice = bidPrice;
            return this;
        }

        public BidAskEntryBuilder withBidVolume(BigDecimal bidVolume) {
            this.bidVolume = bidVolume;
            return this;
        }

        public BidAskEntryBuilder withAskPrice(BigDecimal askPrice) {
            this.askPrice = askPrice;
            return this;
        }

        public BidAskEntryBuilder withAskVolume(BigDecimal askVolume) {
            this.askVolume = askVolume;
            return this;
        }

        public BidAskEntry build() {
            BidAskEntry bidAskEntry = new BidAskEntry();
            bidAskEntry.setBidPrice(bidPrice);
            bidAskEntry.setBidVolume(bidVolume);
            bidAskEntry.setAskPrice(askPrice);
            bidAskEntry.setAskVolume(askVolume);
            return bidAskEntry;
        }
    }
}
