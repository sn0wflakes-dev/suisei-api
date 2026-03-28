package com.sn0w.suisei.api.core.domain.event;

import com.sn0w.suisei.api.core.domain.otp.Code;
import com.sn0w.suisei.api.core.domain.user.Email;

public class GenerateOtpEvent extends Event {
    private final Email email;
    private final String fullName;
    private final Code code;
    private final String minutes;

    private GenerateOtpEvent(
            Email email,
            String name,
            Code code,
            String minutes) {
        this.email = email;
        this.fullName = name;
        this.code = code;
        this.minutes = minutes;
    }

    public static GenerateOtpEvent invoke(
            String email,
            String fullName,
            String code,
            String ttl
    ) {
        return new GenerateOtpEvent(
                Email.of(email),
                fullName,
                Code.of(code),
                ttl

        );
    }

    public Email getEmail() {
        return email;
    }

    public String getFullName() {
        return fullName;
    }

    public Code getCode() {
        return code;
    }

    public String getMinutes() {
        return minutes;
    }
}
