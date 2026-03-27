package com.sn0w.suisei.api.adapter.outbound.event;

import com.sn0w.suisei.api.application.port.outbound.event.EventPublisher;
import com.sn0w.suisei.api.core.domain.event.Event;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class SpringEventPublisherAdapter implements EventPublisher {
    private final ApplicationEventPublisher applicationEventPublisher;

    public SpringEventPublisherAdapter(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    @Override
    public void publish(Event event) {
        applicationEventPublisher.publishEvent(event);
    }
}
