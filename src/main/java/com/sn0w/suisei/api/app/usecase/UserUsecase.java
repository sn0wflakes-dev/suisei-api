package com.sn0w.suisei.api.app.usecase;

import com.sn0w.suisei.api.core.domain.user.User;

public interface UserUsecase {
    User getUserByUsername(String username);
}
