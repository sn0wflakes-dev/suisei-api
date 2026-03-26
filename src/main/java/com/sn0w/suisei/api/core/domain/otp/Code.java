package com.sn0w.suisei.api.core.domain.otp;

import com.sn0w.suisei.api.core.exception.otp.CodeGenerationFail;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class Code {
    private final String value;

    private Code(String value) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("Code can't be null or empty");
        }

        this.value = value;
    }

    public static Code generate() {
        try {
            int bound = (int) Math.pow(10, 6);
            int code = SecureRandom.getInstanceStrong().nextInt(bound);
            return new Code(String.format("%06d", code));
        } catch (NoSuchAlgorithmException e) {
            throw new CodeGenerationFail();
        }
    }

    public String getValue() {
        return value;
    }
}
