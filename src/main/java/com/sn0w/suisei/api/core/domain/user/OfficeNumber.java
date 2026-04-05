package com.sn0w.suisei.api.core.domain.user;

import java.util.regex.Pattern;

public class OfficeNumber {
    private static final Integer MIN_LEN = 3;
    private static final Integer MAX_LEN = 15;
    private static final Pattern REGEXP = Pattern.compile("^[0-9]+$");

    private final String value;

    private OfficeNumber(String value) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("Office phone number can't be null or empty");
        }

        String trimmedValue = value.trim();

        if (!REGEXP.matcher(value).matches()) {
            throw new IllegalArgumentException("Office phone number may only contains digit only");
        }

        if (value.length() < MIN_LEN || value.length() > MAX_LEN) {
            throw new IllegalArgumentException("Office phone number must be between " + MIN_LEN +
                    " and" + MAX_LEN + " characters");
        }

        this.value = trimmedValue;
    }

    public static OfficeNumber of(String value) {
        return new OfficeNumber(value);
    }

    public String getValue() {
        return value;
    }
}
