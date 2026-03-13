package com.sn0w.suisei.api.core.domain.user;

import java.util.UUID;

public class UserId {
    private final String value;

    private UserId(String value) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("User id can't be null or empty");
        }
        this.value = value;
    }

    public static UserId generateId() {
        return new UserId(UUID.randomUUID().toString());
    }

    public static UserId of(String value) {
        return new UserId(value);
    }

    public String getValue() {
        return value;
    }
}
