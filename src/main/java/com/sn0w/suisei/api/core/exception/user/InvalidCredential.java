package com.sn0w.suisei.api.core.exception.user;

import com.sn0w.suisei.api.core.exception.DomainException;

public class InvalidCredential extends DomainException {
    private final static String CODE = "INVALID_CREDENTIAL";
    public InvalidCredential() {
        super(CODE, "Login failed, invalid username/email or password");
    }
}
