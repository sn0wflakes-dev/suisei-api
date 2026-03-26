package com.sn0w.suisei.api.core.exception.user;

import com.sn0w.suisei.api.core.exception.DomainException;

public class UsernameAlreadyExist extends DomainException {

    private static final String CODE = "USERNAME_ALREADY_EXIST";

    public UsernameAlreadyExist(String username) {
        super(CODE, String.format("Username %s already exist", username));
    }
}
