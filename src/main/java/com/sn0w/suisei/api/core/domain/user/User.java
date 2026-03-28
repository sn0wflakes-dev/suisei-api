package com.sn0w.suisei.api.core.domain.user;

import com.sn0w.suisei.api.core.domain.shared.Timestamp;

public class User {
    private final UserId userId;
    private final Username username;
    private final Name name;
    private final Password password;
    private final Email email;
    private final PhoneNumber phoneNumber;
    private final Timestamp timestamp;

    private User(
            UserId userId,
            Username username,
            Name name,
            Password password,
            Email email,
            PhoneNumber phoneNumber,
            Timestamp timestamp) {
        this.userId = userId;
        this.username = username;
        this.name = name;
        this.password = password;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.timestamp = timestamp;
    }

    public static User create(
            String username,
            String password,
            String firstName,
            String lastName,
            String email,
            String phoneNumber
    ) {
        return new User(
                UserId.generateId(),
                Username.of(username),
                Name.of(firstName, lastName),
                HashedPassword.of(password),
                Email.of(email),
                PhoneNumber.of(phoneNumber),
                Timestamp.create()
        );
    }

    public static User reconstruct(
            String userId,
            String username,
            String hashedPassword,
            String firstName,
            String lastName,
            String email,
            String phoneNumber,
            Timestamp timestamp
    ) {
        return new User(
                UserId.of(userId),
                Username.of(username),
                Name.of(firstName, lastName),
                HashedPassword.of(hashedPassword),
                Email.of(email),
                PhoneNumber.of(phoneNumber),
                timestamp
        );
    }

    public UserId getUserId() {
        return userId;
    }

    public Username getUsername() {
        return username;
    }

    public Password getPassword() {
        return password;
    }

    public Email getEmail() {
        return email;
    }

    public PhoneNumber getPhoneNumber() {
        return phoneNumber;
    }

    public Name getName() {
        return name;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }
}
