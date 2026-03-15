package com.sn0w.suisei.api.core.domain.otp;

public class Otp {
    private final Id id;
    private final Code code;

    private Otp(Id id, Code code) {
        this.id = id;
        this.code = code;
    }

    public static Otp of(String username) {
        return new Otp(
                Id.of(username),
                Code.generate()
        );
    }

    public Id getId() {
        return id;
    }

    public Code getCode() {
        return code;
    }
}
