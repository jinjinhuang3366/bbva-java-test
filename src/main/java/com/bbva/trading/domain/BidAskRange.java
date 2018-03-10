package com.bbva.trading.domain;

import java.io.Serializable;

public class BidAskRange implements Serializable {

    private double bidRangeStart;
    private double bidRangeEnd;
    private double askRangeStart;
    private double askRangeEnd;
    private double volumeRangeStart;
    private double volumeRangeEnd;

    public double getBidRangeStart() {
        return bidRangeStart;
    }

    public void setBidRangeStart(double bidRangeStart) {
        this.bidRangeStart = bidRangeStart;
    }

    public double getBidRangeEnd() {
        return bidRangeEnd;
    }

    public void setBidRangeEnd(double bidRangeEnd) {
        this.bidRangeEnd = bidRangeEnd;
    }

    public double getAskRangeStart() {
        return askRangeStart;
    }

    public void setAskRangeStart(double askRangeStart) {
        this.askRangeStart = askRangeStart;
    }

    public double getAskRangeEnd() {
        return askRangeEnd;
    }

    public void setAskRangeEnd(double askRangeEnd) {
        this.askRangeEnd = askRangeEnd;
    }

    public double getVolumeRangeStart() {
        return volumeRangeStart;
    }

    public void setVolumeRangeStart(double volumeRangeStart) {
        this.volumeRangeStart = volumeRangeStart;
    }

    public double getVolumeRangeEnd() {
        return volumeRangeEnd;
    }

    public void setVolumeRangeEnd(double volumeRangeEnd) {
        this.volumeRangeEnd = volumeRangeEnd;
    }

    public static final class BidAskRangeBuilder {
        private double bidRangeStart;
        private double bidRangeEnd;
        private double askRangeStart;
        private double askRangeEnd;
        private double volumeRangeStart;
        private double volumeRangeEnd;

        private BidAskRangeBuilder() {
        }

        public static BidAskRangeBuilder aBidAskRange() {
            return new BidAskRangeBuilder();
        }

        public BidAskRangeBuilder withBidRangeStart(double bidRangeStart) {
            this.bidRangeStart = bidRangeStart;
            return this;
        }

        public BidAskRangeBuilder withBidRangeEnd(double bidRangeEnd) {
            this.bidRangeEnd = bidRangeEnd;
            return this;
        }

        public BidAskRangeBuilder withAskRangeStart(double askRangeStart) {
            this.askRangeStart = askRangeStart;
            return this;
        }

        public BidAskRangeBuilder withAskRangeEnd(double askRangeEnd) {
            this.askRangeEnd = askRangeEnd;
            return this;
        }

        public BidAskRangeBuilder withVolumeRangeStart(double volumeRangeStart) {
            this.volumeRangeStart = volumeRangeStart;
            return this;
        }

        public BidAskRangeBuilder withVolumeRangeEnd(double volumeRangeEnd) {
            this.volumeRangeEnd = volumeRangeEnd;
            return this;
        }

        public BidAskRange build() {
            BidAskRange bidAskRange = new BidAskRange();
            bidAskRange.setBidRangeStart(bidRangeStart);
            bidAskRange.setBidRangeEnd(bidRangeEnd);
            bidAskRange.setAskRangeStart(askRangeStart);
            bidAskRange.setAskRangeEnd(askRangeEnd);
            bidAskRange.setVolumeRangeStart(volumeRangeStart);
            bidAskRange.setVolumeRangeEnd(volumeRangeEnd);
            return bidAskRange;
        }
    }
}
