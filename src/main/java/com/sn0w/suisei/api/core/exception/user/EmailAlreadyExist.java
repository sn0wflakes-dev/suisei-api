package com.sn0w.suisei.api.core.exception.user;

import com.sn0w.suisei.api.core.exception.DomainException;

public class EmailAlreadyExist extends DomainException {

    private static final String CODE = "EMAIL_ALREADY_EXIST";

    public EmailAlreadyExist(String email) {
        super(CODE, String.format("Email %s is already used", email));
    }

}
