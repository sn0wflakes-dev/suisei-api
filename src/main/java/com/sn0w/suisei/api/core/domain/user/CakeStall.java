package com.sn0w.suisei.api.core.domain.user;

import java.util.regex.Pattern;

public class CakeStall {
    private static final Pattern REGEXP = Pattern.compile("^[a-zA-Z0-9 ]+$");

    private static final int MIN_LEN = 3;
    private static final int MAX_LEN = 100;

    private final String value;

    public static CakeStall of(String value) {
        return new CakeStall(value);
    }

    private CakeStall(String value) {

        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("Cake stall name can't be null or empty");
        }

        String trimmedValue = value.trim();

        if (trimmedValue.length() < MIN_LEN || trimmedValue.length() > MAX_LEN) {
            throw new IllegalArgumentException("Cake stall name must be between " + MIN_LEN +
                    " and" + MAX_LEN + " characters");
        }

        if (REGEXP.matcher(trimmedValue).matches()) {
            throw new IllegalArgumentException("Cake stall name may only contain letters," +
                    " and spaces");
        }

        this.value = trimmedValue;
    }

    public String getValue() {
        return value;
    }
}
