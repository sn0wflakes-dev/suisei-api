package com.sn0w.suisei.api.app.event;

import com.sn0w.suisei.api.core.domain.event.Event;

public interface EventPublisher {
    void publish(Event event);
}
