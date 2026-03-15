package com.sn0w.suisei.api.core.exception.otp;

import com.sn0w.suisei.api.core.exception.DomainException;

public class OtpNotMatch extends DomainException {
    private static final String CODE = "INVALID_OTP";

    public OtpNotMatch() {
        super(CODE, "Invalid otp code");
    }
}
