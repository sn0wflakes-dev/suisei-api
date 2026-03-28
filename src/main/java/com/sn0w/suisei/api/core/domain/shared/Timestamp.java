package com.sn0w.suisei.api.core.domain.shared;

import java.time.OffsetDateTime;

public class Timestamp {
    private final OffsetDateTime createdAt;
    private final OffsetDateTime updatedAt;

    public static Timestamp create() {
        OffsetDateTime now = OffsetDateTime.now();
        return new Timestamp(now, now);
    }

    public static Timestamp of(
            OffsetDateTime createdAt,
            OffsetDateTime updatedAt) {
        return new Timestamp(createdAt, updatedAt);
    }

    private Timestamp(
            OffsetDateTime createdAt,
            OffsetDateTime updatedAt) {
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public OffsetDateTime getCreatedAt() {
        return createdAt;
    }

    public OffsetDateTime getUpdatedAt() {
        return updatedAt;
    }
}
