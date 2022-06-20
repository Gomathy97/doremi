package com.example.geektrust.enums;

public enum SubscriptionType {
    FREE(1),
    PERSONAL(1),
    PREMIUM(3);

    private final int value;

    SubscriptionType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
