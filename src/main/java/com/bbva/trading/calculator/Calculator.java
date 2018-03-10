package com.bbva.trading.calculator;

public interface Calculator<T, R> {
    R calculate(T data);
}
