package com.bbva.trading.listener;

public interface Listener<T> {
    void onUpdate(T data);
}
