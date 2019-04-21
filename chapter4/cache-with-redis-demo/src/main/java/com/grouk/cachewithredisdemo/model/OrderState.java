package com.grouk.cachewithredisdemo.model;

public enum OrderState {
    /**
     *
     */
    INIT,
    /**
     * 已支付
     */
    PAID,
    BREWING,
    BREWED,
    TAKEN,
    CANCELLED
}
