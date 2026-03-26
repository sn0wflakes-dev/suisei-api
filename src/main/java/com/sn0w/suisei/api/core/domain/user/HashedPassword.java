package com.sn0w.suisei.api.core.domain.user;

public final class HashedPassword implements Password {
    private final String value;

    private HashedPassword(String value) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("Hashed Password can't be null or empty");
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
