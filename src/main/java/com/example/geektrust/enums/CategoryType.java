package com.example.geektrust.enums;

public enum CategoryType {
    MUSIC(new long[]{100, 250}),
    VIDEO(new long[]{200, 500}),
    PODCAST(new long[]{100, 300});

    private final long[] value;
    CategoryType(long[] value) {
        this.value = value;
    }

    public long[] getValue() {
        return value;
    }
}
