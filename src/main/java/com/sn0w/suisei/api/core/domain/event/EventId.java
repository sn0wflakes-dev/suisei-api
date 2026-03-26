package com.sn0w.suisei.api.core.domain.event;

import java.util.UUID;

public class EventId {
    private final String value;

    private EventId(String value) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("Event id can't be null or empty");
        }

        this.value = value;
    }

    public static EventId generate() {
        return new EventId(UUID.randomUUID().toString());
    }

    public String getValue() {
        return value;
    }
}
