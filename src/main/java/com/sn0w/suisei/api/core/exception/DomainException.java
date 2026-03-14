package com.sn0w.suisei.api.core.exception;

public abstract class DomainException extends RuntimeException {
    private final String code;

    public DomainException(String code, String  message) {
        super(message);
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
