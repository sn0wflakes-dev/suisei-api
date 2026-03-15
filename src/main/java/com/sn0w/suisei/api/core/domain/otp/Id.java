package com.sn0w.suisei.api.core.domain.otp;

public class Id {
    private final String username;

    private Id(String username) {
        if (username == null || username.isBlank()) {
            throw new IllegalArgumentException("Id or username can't be null or empty");
        }

        this.username = username;
    }

    public static Id of(String value) {
        return new Id(
                value
        );
    }

    public String getUsername() {
        return username;
    }
}
