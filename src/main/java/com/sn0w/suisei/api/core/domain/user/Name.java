package com.sn0w.suisei.api.core.domain.user;

import java.util.regex.Pattern;

public class Name {
    private static final Integer MIN_LEN = 3;
    private static final Integer MAX_LEN = 255;
    private static final Pattern REGEXP = Pattern.compile("^[a-zA-Z ]+$");
    private final String fullName;
    private final String firstName;
    private final String lastName;

    private Name(String firstName, String lastName) {
        if (firstName == null || firstName.isBlank()) {
            throw new IllegalArgumentException("First name can't be null or empty");
        }

        if (lastName == null || lastName.isBlank()) {
            throw new IllegalArgumentException("Last name can't be null or empty");
        }

        String trimmedFirstName = firstName.trim();
        String trimmedLastName = lastName.trim();

        if (!REGEXP.matcher(trimmedFirstName).matches() || !REGEXP.matcher(trimmedLastName).matches()) {
            throw new IllegalArgumentException("Name may only contain letters," +
                    " and spaces");
        }

        this.fullName = trimmedFirstName+ " " +trimmedLastName;

        if (fullName.length() < MIN_LEN || fullName.length() > MAX_LEN) {
            throw new IllegalArgumentException("Full name must be between " + MIN_LEN +
                    " and" + MAX_LEN + " characters");
        }

        this.firstName = trimmedFirstName;
        this.lastName = trimmedLastName;

    }

    public static Name of(String firstName, String lastName) {
        return new Name(firstName, lastName);
    }

    public String getFullName() {
        return fullName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
}
