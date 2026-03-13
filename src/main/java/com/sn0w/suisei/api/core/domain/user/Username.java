package com.sn0w.suisei.api.core.domain.user;

import java.util.regex.Pattern;

public class Username {
    private static final Integer MAX_LEN = 30;
    private static final Integer MIN_LEN = 3;
    private static final Pattern REGEXP = Pattern.compile("^[a-zA-Z0-9-_]+$");

    private final String value;

    private Username(String value) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("Username can't be null or empty");
        }

        String trimmedValue = value.trim();

        if (!REGEXP.matcher(trimmedValue).matches()) {
            throw new IllegalArgumentException("Username may only contain letters," +
                    " numbers, hyphens (-), and underscores (_)");
        }

        if (trimmedValue.length() > MAX_LEN || trimmedValue.length() < MIN_LEN) {
            throw new IllegalArgumentException("Username must be between " + MIN_LEN +
                    " and" + MAX_LEN + " characters");
        }

        this.value = trimmedValue;
    }

    public static Username of(String value) {
        return new Username(value);
    }

    public String getValue() {
        return value;
    }
}
