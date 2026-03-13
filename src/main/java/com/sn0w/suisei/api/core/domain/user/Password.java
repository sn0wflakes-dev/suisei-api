package com.sn0w.suisei.api.core.domain.user;

public class Password {
    private final String value;

    private Password(String value) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("Password can't be null or empty");
        }

        this.value = value;
    }

    public static Password of(String value) {
        return new Password(value);
    }

    public String getValue() {
        return value;
    }
}
