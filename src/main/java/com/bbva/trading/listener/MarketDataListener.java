package com.bbva.trading.listener;

import com.bbva.trading.calculator.Calculator;
import com.bbva.trading.domain.OrderBookEntry;
import io.reactivex.Flowable;
import io.reactivex.schedulers.Schedulers;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.LinkedBlockingQueue;

public class MarketDataListener implements Listener<OrderBookEntry> {
    private final ConcurrentMap<String, BlockingQueue<OrderBookEntry>> entriesByInstrument = new ConcurrentHashMap();
    private Calculator calculator;

    public MarketDataListener(Calculator calculator) {
        this.calculator = calculator;
    }

    @Override
    public void onUpdate(OrderBookEntry data) {
        String instrument = data.getInstrument();
        if (!entriesByInstrument.containsKey(instrument)) {
            BlockingQueue queue = new LinkedBlockingQueue();
            queue.offer(data);
            entriesByInstrument.putIfAbsent(instrument, queue);
        } else {
            entriesByInstrument.get(instrument).offer(data);
        }

        concurrentCalculateVWAP();
    }

    private void concurrentCalculateVWAP() {
        Flowable.fromIterable(entriesByInstrument.keySet())
                .parallel()
                .runOn(Schedulers.computation())
                .map(i -> calculator.calculate(entriesByInstrument.get(i).take()))
                .sequential()
                .subscribe(vwap -> {
                });
    }
}
