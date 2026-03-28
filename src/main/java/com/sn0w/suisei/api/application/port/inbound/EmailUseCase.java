package com.sn0w.suisei.api.application.port.inbound;

import com.sn0w.suisei.api.core.domain.event.GenerateOtpEvent;
import com.sn0w.suisei.api.core.domain.event.UserRegisteredEvent;

public interface EmailUseCase {
    void welcomeMail(UserRegisteredEvent event);
    void otpMail(GenerateOtpEvent event);
}
