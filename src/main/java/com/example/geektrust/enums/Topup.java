package com.example.geektrust.enums;

public enum Topup {
    FOUR_DEVICE(50),
    TEN_DEVICE(100);

    private final long value;

    Topup(long value) { this.value = value;}

    public long getValue() {
        return value;
    }
}
