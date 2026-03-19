package com.sn0w.suisei.api.core.domain.event;

import java.time.OffsetDateTime;

public abstract class Event {
    private final EventId eventId;
    private final OffsetDateTime occurredAt;

    protected Event() {
        this.eventId = EventId.generate();
        this.occurredAt = OffsetDateTime.now();
    }

    public EventId getEventId() {
        return eventId;
    }

    public OffsetDateTime getOccurredAt() {
        return occurredAt;
    }
}
