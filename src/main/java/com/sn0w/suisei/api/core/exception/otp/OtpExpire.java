package com.sn0w.suisei.api.core.exception.otp;

import com.sn0w.suisei.api.core.exception.DomainException;

public class OtpExpire extends DomainException {

    private static String CODE = "OTP_EXPIRE";

    public OtpExpire() {
        super(CODE, "Otp code already expire");
    }
}
