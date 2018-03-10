package com.bbva.trading.simulator;

import com.bbva.trading.calculator.VWAPCalculator;
import com.bbva.trading.listener.MarketDataListener;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

public class OrderBookSimulatorTest {

    private Simulator simulator;

    @Before
    public void setUp() throws Exception {
        simulator = new OrderBookSimulator("ESB", Arrays.asList("EURUSD", "GBPUSD"),
                10, 5, new MarketDataListener(new VWAPCalculator()));
    }

    @Test
    public void start() throws Exception {
        simulator.start();
    }
}