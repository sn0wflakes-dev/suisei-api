package com.sn0w.suisei.api.application.port.outbound.repository;

import com.sn0w.suisei.api.core.domain.user.User;
import com.sn0w.suisei.api.core.domain.user.UserId;

public interface UserRepository {
    void addUser(User user);
    User deleteUserById(String userId);
    Boolean verifyUserById(String userId);
    User findUserByUsername(String username);
}
