package com.bbva.trading;

import com.bbva.trading.calculator.VWAPCalculator;
import com.bbva.trading.domain.OrderBookEntry;
import com.bbva.trading.listener.Listener;
import com.bbva.trading.listener.MarketDataListener;
import com.bbva.trading.simulator.OrderBookSimulator;
import com.bbva.trading.simulator.Simulator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Application {

    public static void main(String[] args) {
        final Listener<OrderBookEntry> listener = new MarketDataListener(new VWAPCalculator());
        final List<Simulator> simulators = new ArrayList<>();

        getSimulatedMarketVenues().stream().forEach(venue -> {
            OrderBookSimulator simulator = new OrderBookSimulator(venue, getSimulatedInstruments(), 10, 100, listener);
            simulators.add(simulator);

            simulator.start();
        });

        Runtime.getRuntime().addShutdownHook(new Thread(()->{
            for(Simulator simulator : simulators) {
                simulator.stop();
            }
        }));
    }

    private static List<String> getSimulatedMarketVenues() {
        return Arrays.asList("EBS", "Reuters", "AutoBahn");
    }

    private static List<String> getSimulatedInstruments() {
        return Arrays.asList("EURUSD", "USDJPY", "GBPUSD", "USDCHF");
    }


}
