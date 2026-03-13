package com.sn0w.suisei.api.core.domain.user;

public class HashedPassword {
    private final String value;

    private HashedPassword(String value) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("Password can't be null or empty");
        }

        this.value = value;
    }

    public static HashedPassword of(String value) {
        return new HashedPassword(value);
    }

    public String getValue() {
        return value;
    }
}
