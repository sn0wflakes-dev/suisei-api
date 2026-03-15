package com.sn0w.suisei.api.core.domain.email;

public class Template {
    private final String value;

    private Template(String value) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("Template path can't be null or empty");
        }

        this.value = value;
    }

    public static Template of(String value) {
        return new Template(value);
    }

    public String getValue() {
        return value;
    }
}
