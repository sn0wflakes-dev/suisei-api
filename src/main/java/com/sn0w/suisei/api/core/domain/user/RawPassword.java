package com.sn0w.suisei.api.core.domain.user;

import java.util.regex.Pattern;

public final class RawPassword implements Password {
    private static final Pattern REGEXP = Pattern.compile("^" +
            "(?=.*[a-z])" +
            "(?=.*[A-Z])" +
            "(?=.*\\d)" +
            "(?=.*[~!`@#\"$;%'.<>^&*()\\-?+}_|={:])" +
            ".*$");

    private static final int MIN_LEN = 16;
    private static final int MAX_LEN = 64;

    private final String value;

    private RawPassword(String value) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("Password can't be null or empty");
        }

        String trimmedValue = value.trim();

        if (trimmedValue.length() < MIN_LEN || trimmedValue.length() > MAX_LEN) {
            throw new IllegalArgumentException("Password must be between " +
                    MIN_LEN + " and " + MAX_LEN + " characters long");
        }

        if (!REGEXP.matcher(trimmedValue).matches()) {
            throw new IllegalArgumentException("Password must contain at least one lowercase letter," +
                    " one uppercase letter, one digit, and one special character");
        }

        this.value = trimmedValue;
    }

    public static RawPassword of(String value) {
        return new RawPassword(value);
    }

    public String getValue() {
        return value;
    }


}
