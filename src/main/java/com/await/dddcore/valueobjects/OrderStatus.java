package com.await.dddcore.valueobjects;

public enum OrderStatus {
    NEW,
    CONFIRMED,
    SHIPPED,
    DELIVERED;

    public boolean isCompleted() {
        return this == DELIVERED;
    }
}
