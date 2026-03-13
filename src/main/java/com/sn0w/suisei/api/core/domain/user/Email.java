package com.sn0w.suisei.api.core.domain.user;

import java.util.regex.Pattern;

public class Email {
    private static final Pattern REGEXP = Pattern.compile(
            "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+.[a-zA-Z]{2,6}$",
            Pattern.CASE_INSENSITIVE);

    private final String value;

    private Email(String value) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("Email can't be null or empty");
        }

        if (!REGEXP.matcher(value).matches()) {
            throw new IllegalArgumentException("Invalid email format, expected format: user@example.com");
        }

        this.value = value;
    }

    public static Email of(String value) {
        return new Email(value);
    }

    public String getValue() {
        return value;
    }
}
