package com.sn0w.suisei.api.adapter.inbound.event;

import com.sn0w.suisei.api.application.port.inbound.EmailUseCase;
import com.sn0w.suisei.api.core.domain.event.UserRegisteredEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class UserRegisteredEventListener {

    private final EmailUseCase emailUseCase;

    public UserRegisteredEventListener(EmailUseCase emailUseCase) {
        this.emailUseCase = emailUseCase;
    }

    @Async
    @EventListener
    public void handleEmail(UserRegisteredEvent event) {
        emailUseCase.welcomeMail(event);
    }

}
