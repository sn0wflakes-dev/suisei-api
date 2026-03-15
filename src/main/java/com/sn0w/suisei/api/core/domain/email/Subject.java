package com.sn0w.suisei.api.core.domain.email;

import java.util.regex.Pattern;

public class Subject {
    private static final Pattern REGEXP = Pattern.compile("^[a-zA-Z0-9 _-]+$");
    private static final Integer MIN_LEN = 3;
    private static final Integer MAX_LEN = 100;

    private final String value;

    public Subject(String value) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("Subject email can't be null or empty");
        }

        if (!REGEXP.matcher(value).matches()) {
            throw new IllegalArgumentException("Invalid subject format, subject only contain letters, number, under score (_) and hyphen (-)");
        }

        if (value.length() < MIN_LEN || value.length() > MAX_LEN) {
            throw new IllegalArgumentException("Subject email must be between " + MIN_LEN +
                    " and" + " " + MAX_LEN + " characters");
        }

        this.value = value;
    }

    public static Subject of(String value) {
        return new Subject(value);
    }

    public String getValue() {
        return value;
    }
}
