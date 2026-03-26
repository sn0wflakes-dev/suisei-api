package com.sn0w.suisei.api.application.port.outbound.gateway;

import com.sn0w.suisei.api.core.domain.email.Email;

public interface EmailUsecase {
    void send(Email email);
}
