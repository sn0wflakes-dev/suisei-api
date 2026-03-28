package com.sn0w.suisei.api.application.service;

import com.sn0w.suisei.api.application.port.inbound.EmailUseCase;
import com.sn0w.suisei.api.application.port.outbound.gateway.EmailGatewayPort;
import com.sn0w.suisei.api.core.domain.email.Email;
import com.sn0w.suisei.api.core.domain.event.GenerateOtpEvent;
import com.sn0w.suisei.api.core.domain.event.UserRegisteredEvent;
import org.springframework.stereotype.Service;

@Service
public class EmailService implements EmailUseCase {

    private final EmailGatewayPort email;

    public EmailService(EmailGatewayPort email) {
        this.email = email;
    }

    @Override
    public void welcomeMail(UserRegisteredEvent event) {
        email.send(Email.welcomeMail(
                event.getEmail().getValue(),
                event.getName().getFullName(),
                event.getRegisteredAt()
        ));
    }

    @Override
    public void otpMail(GenerateOtpEvent event) {
        email.send(Email.otpMail(
                event.getEmail().getValue(),
                event.getFullName(),
                event.getCode().getValue(),
                event.getMinutes())
        );
    }
}
