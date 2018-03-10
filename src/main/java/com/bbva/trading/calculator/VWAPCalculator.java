package com.bbva.trading.calculator;

import com.bbva.trading.domain.BidAskEntry;
import com.bbva.trading.domain.OrderBookEntry;
import com.bbva.trading.domain.VWAPResult;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;

public class VWAPCalculator implements Calculator<OrderBookEntry, VWAPResult> {

    @Override
    public VWAPResult calculate(OrderBookEntry entry) {
        BigDecimal sumOfBidPrices = BigDecimal.ZERO;
        BigDecimal sumOfBidVolume = BigDecimal.ZERO;
        BigDecimal sumOfAskPrices = BigDecimal.ZERO;
        BigDecimal sumOfAskVolume = BigDecimal.ZERO;

        for (BidAskEntry bidAskEntry : entry.getBidAskEntries()) {
            sumOfBidPrices = sumOfBidPrices.add(bidAskEntry.getBidPrice().multiply(bidAskEntry.getBidVolume()));
            sumOfBidVolume = sumOfBidVolume.add(bidAskEntry.getBidVolume());
            sumOfAskPrices = sumOfAskPrices.add(bidAskEntry.getAskPrice().multiply(bidAskEntry.getAskVolume()));
            sumOfAskVolume = sumOfAskVolume.add(bidAskEntry.getAskVolume());
        }

        VWAPResult result =  VWAPResult.VWAPResultBuilder.aVWAPResult()
                .withInstrument(entry.getInstrument())
                .withTimeStamp(LocalDateTime.now())
                .withBid(sumOfBidPrices.divide(sumOfBidVolume, 4, RoundingMode.HALF_UP))
                .withAsk(sumOfAskPrices.divide(sumOfAskVolume, 4, RoundingMode.HALF_UP))
                .build();

        System.out.println(Thread.currentThread().getName() + "-" + result);
        return result;
    }
}
