package com.sn0w.suisei.api.application.port.inbound;

import com.sn0w.suisei.api.core.domain.user.User;

public interface AuthenticationUsecase {
    void register(User user);
    User login(String identifier, String password);
    // TODO : create service for check identifier
    // TODO : create service for check email
    // TODO : create service for check phone number
}
