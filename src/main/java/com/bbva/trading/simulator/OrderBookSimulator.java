package com.bbva.trading.simulator;


import com.bbva.trading.domain.BidAskEntry;
import com.bbva.trading.domain.BidAskRange;
import com.bbva.trading.domain.OrderBookEntry;
import com.bbva.trading.listener.Listener;
import io.reactivex.Flowable;
import io.reactivex.schedulers.Schedulers;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class OrderBookSimulator implements Simulator {
    private String venue;
    private List<String> instruments;
    private int level; // market depth level
    private long interval; // interval to emit order book entry
    private Listener marketDataListener;
    private static final int BACK_PRESSURE_BUFFER_CAPACITY = 100000;

    public OrderBookSimulator(String venue, List<String> instruments, int level, long interval, Listener<OrderBookEntry> listener) {
        this.venue = venue;
        this.instruments = instruments;
        this.level = level;
        this.interval = interval;
        this.marketDataListener = listener;
    }

    public void start() {
        Flowable.fromIterable(instruments)
                .onBackpressureBuffer(BACK_PRESSURE_BUFFER_CAPACITY)
                .parallel()
                .runOn(Schedulers.computation())
                .flatMap(instrument -> Flowable.interval(this.interval, TimeUnit.MILLISECONDS).map(i -> createOrderBookEntry(instrument)))
                .sequential()
                .blockingSubscribe(entry -> marketDataListener.onUpdate(entry));
    }

    @Override
    public void stop() {
        // TODO Stop and clean up the simulator
    }

    private OrderBookEntry createOrderBookEntry(String instrument) {
        List<BidAskEntry> bidAskEntries = new ArrayList<>();
        for (int i = 0; i < this.level; i++) {
            BidAskRange range = createBidAskRange(instrument);
            bidAskEntries.add(BidAskEntry.BidAskEntryBuilder.aBidAskEntry()
                    .withBidPrice(BigDecimal.valueOf(ThreadLocalRandom.current().nextDouble(range.getBidRangeStart(), range.getBidRangeEnd())).setScale(3, RoundingMode.HALF_UP))
                    .withBidVolume(BigDecimal.valueOf(ThreadLocalRandom.current().nextDouble(range.getVolumeRangeStart(), range.getVolumeRangeEnd())))
                    .withAskPrice(BigDecimal.valueOf(ThreadLocalRandom.current().nextDouble(range.getAskRangeStart(), range.getAskRangeEnd())).setScale(3, RoundingMode.HALF_UP))
                    .withAskVolume(BigDecimal.valueOf(ThreadLocalRandom.current().nextDouble(range.getVolumeRangeStart(), range.getVolumeRangeEnd())))
                    .build()
            );
        }

        return OrderBookEntry.OrderBookEntryBuilder.anOrderBookEntry()
                .withVenue(this.venue)
                .withInstrument(instrument)
                .withLevel(this.level)
                .withTimeStamp(LocalDateTime.now())
                .withBidAskEntries(bidAskEntries)
                .build();
    }

    private BidAskRange createBidAskRange(String instrument) {
        switch (instrument) {
            case "EURUSD":
                return BidAskRange.BidAskRangeBuilder.aBidAskRange().withBidRangeStart(1.2d).withBidRangeEnd(1.3d)
                        .withAskRangeStart(1.4d).withAskRangeEnd(1.5d).withVolumeRangeStart(10000d).withVolumeRangeEnd(1000000000d).build();
            case "USDJPY":
                return BidAskRange.BidAskRangeBuilder.aBidAskRange().withBidRangeStart(100.0d).withBidRangeEnd(105.0d)
                        .withAskRangeStart(106.0d).withAskRangeEnd(110.0d).withVolumeRangeStart(10000d).withVolumeRangeEnd(1000000000d).build();
            case "GBPUSD":
                return BidAskRange.BidAskRangeBuilder.aBidAskRange().withBidRangeStart(1.3d).withBidRangeEnd(1.5d)
                        .withAskRangeStart(1.6d).withAskRangeEnd(1.8d).withVolumeRangeStart(10000d).withVolumeRangeEnd(1000000000d).build();
            case "USDCHF":
                return BidAskRange.BidAskRangeBuilder.aBidAskRange().withBidRangeStart(0.9d).withBidRangeEnd(1.0d)
                        .withAskRangeStart(1.1d).withAskRangeEnd(1.3d).withVolumeRangeStart(10000d).withVolumeRangeEnd(1000000000d).build();
            default:
                return BidAskRange.BidAskRangeBuilder.aBidAskRange().withBidRangeStart(1.2d).withBidRangeEnd(1.3d)
                        .withAskRangeStart(1.4d).withAskRangeEnd(1.5d).withVolumeRangeStart(10000d).withVolumeRangeEnd(1000000000d).build();
        }
    }
}
