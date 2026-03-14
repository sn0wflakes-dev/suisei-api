package com.sn0w.suisei.api.core.exception.user;

import com.sn0w.suisei.api.core.exception.DomainException;

public class PhoneNumberAlreadyExist extends DomainException {

    private static final String CODE = "PHONE_NUMBER_ALREADY_EXIST";

    public PhoneNumberAlreadyExist(String phoneNumber) {
        super(CODE, String.format("Phone Number %s is already exist", phoneNumber));
    }
}
