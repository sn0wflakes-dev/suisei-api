package com.sn0w.suisei.api.core.exception.user;

import com.sn0w.suisei.api.core.exception.DomainException;

public class UserNotFound extends DomainException {
    private static final String CODE = "USER_NOT_FOUND";
    public UserNotFound(String username) {
        super(CODE, String.format("User with username %s not found", username));
    }
}
