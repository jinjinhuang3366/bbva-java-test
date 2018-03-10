package com.bbva.trading.domain;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

public class OrderBookEntry implements Serializable {
    private String venue;
    private String instrument;
    private List<BidAskEntry> bidAskEntries;
    private LocalDateTime timeStamp;
    private int level;

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public String getInstrument() {
        return instrument;
    }

    public void setInstrument(String instrument) {
        this.instrument = instrument;
    }

    public List<BidAskEntry> getBidAskEntries() {
        return bidAskEntries;
    }

    public void setBidAskEntries(List<BidAskEntry> bidAskEntries) {
        this.bidAskEntries = bidAskEntries;
    }

    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(LocalDateTime timeStamp) {
        this.timeStamp = timeStamp;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    @Override
    public String toString() {
        return "OrderBookEntry{" +
                "venue='" + venue + '\'' +
                ", instrument='" + instrument + '\'' +
                ", bidAskEntries=" + bidAskEntries +
                ", timeStamp=" + timeStamp +
                ", level=" + level +
                '}';
    }

    public static final class OrderBookEntryBuilder {
        private String venue;
        private String instrument;
        private List<BidAskEntry> bidAskEntries;
        private LocalDateTime timeStamp;
        private int level;

        private OrderBookEntryBuilder() {
        }

        public static OrderBookEntryBuilder anOrderBookEntry() {
            return new OrderBookEntryBuilder();
        }

        public OrderBookEntryBuilder withVenue(String venue) {
            this.venue = venue;
            return this;
        }

        public OrderBookEntryBuilder withInstrument(String instrument) {
            this.instrument = instrument;
            return this;
        }

        public OrderBookEntryBuilder withBidAskEntries(List<BidAskEntry> bidAskEntries) {
            this.bidAskEntries = bidAskEntries;
            return this;
        }

        public OrderBookEntryBuilder withTimeStamp(LocalDateTime timeStamp) {
            this.timeStamp = timeStamp;
            return this;
        }

        public OrderBookEntryBuilder withLevel(int level) {
            this.level = level;
            return this;
        }

        public OrderBookEntry build() {
            OrderBookEntry orderBookEntry = new OrderBookEntry();
            orderBookEntry.setVenue(venue);
            orderBookEntry.setInstrument(instrument);
            orderBookEntry.setBidAskEntries(bidAskEntries);
            orderBookEntry.setTimeStamp(timeStamp);
            orderBookEntry.setLevel(level);
            return orderBookEntry;
        }
    }
}
