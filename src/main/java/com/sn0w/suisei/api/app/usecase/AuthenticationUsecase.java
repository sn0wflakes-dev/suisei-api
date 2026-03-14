package com.sn0w.suisei.api.app.usecase;

import com.sn0w.suisei.api.core.domain.user.User;

public interface AuthenticationUsecase {
    void register(User user);
    User login(String username, String password);
    // TODO : create service for check username
    // TODO : create service for check email
    // TODO : create service for check phone number
}
