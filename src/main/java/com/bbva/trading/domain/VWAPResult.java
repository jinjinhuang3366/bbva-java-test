package com.bbva.trading.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class VWAPResult implements Serializable{

    private String instrument;
    private LocalDateTime timeStamp;
    private BigDecimal bid;
    private BigDecimal ask;

    public String getInstrument() {
        return instrument;
    }

    public void setInstrument(String instrument) {
        this.instrument = instrument;
    }

    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(LocalDateTime timeStamp) {
        this.timeStamp = timeStamp;
    }

    public BigDecimal getBid() {
        return bid;
    }

    public void setBid(BigDecimal bid) {
        this.bid = bid;
    }

    public BigDecimal getAsk() {
        return ask;
    }

    public void setAsk(BigDecimal ask) {
        this.ask = ask;
    }

    @Override
    public String toString() {
        return "VWAPResult{" +
                "instrument='" + instrument + '\'' +
                ", timeStamp=" + timeStamp +
                ", bid=" + bid +
                ", ask=" + ask +
                '}';
    }

    public static final class VWAPResultBuilder {
        private String instrument;
        private LocalDateTime timeStamp;
        private BigDecimal bid;
        private BigDecimal ask;

        private VWAPResultBuilder() {
        }

        public static VWAPResultBuilder aVWAPResult() {
            return new VWAPResultBuilder();
        }

        public VWAPResultBuilder withInstrument(String instrument) {
            this.instrument = instrument;
            return this;
        }

        public VWAPResultBuilder withTimeStamp(LocalDateTime timeStamp) {
            this.timeStamp = timeStamp;
            return this;
        }

        public VWAPResultBuilder withBid(BigDecimal bid) {
            this.bid = bid;
            return this;
        }

        public VWAPResultBuilder withAsk(BigDecimal ask) {
            this.ask = ask;
            return this;
        }

        public VWAPResult build() {
            VWAPResult vWAPResult = new VWAPResult();
            vWAPResult.setInstrument(instrument);
            vWAPResult.setTimeStamp(timeStamp);
            vWAPResult.setBid(bid);
            vWAPResult.setAsk(ask);
            return vWAPResult;
        }
    }
}
