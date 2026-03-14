package com.sn0w.suisei.api.core.repository;

import com.sn0w.suisei.api.core.domain.user.User;
import com.sn0w.suisei.api.core.domain.user.UserId;

public interface UserRepository {
    void addUser(User user);
    User deleteUserById(String userId);
    User updateUserById(String userId);
}
