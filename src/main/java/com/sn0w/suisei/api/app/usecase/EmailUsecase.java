package com.sn0w.suisei.api.app.usecase;

import com.sn0w.suisei.api.core.domain.email.Email;

public interface EmailUsecase {
    void send(Email email);
}
