package com.sn0w.suisei.api.core.domain.user;

public class User {
    private final UserId userId;
    private final Username username;
    private final Name name;
    private final Password password;
    private final Email email;
    private final PhoneNumber phoneNumber;

    private User(
            UserId userId,
            Username username,
            Name name,
            Password password,
            Email email,
            PhoneNumber phoneNumber) {
        this.userId = userId;
        this.username = username;
        this.name = name;
        this.password = password;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public static User of(
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
                Password.of(password),
                Email.of(email),
                PhoneNumber.of(phoneNumber)
        );
    }

    public static User reconstruct(
            String userId,
            String username,
            String hashedPassword,
            String firstName,
            String lastName,
            String email,
            String phoneNumber
    ) {
        return new User(
                UserId.of(userId),
                Username.of(username),
                Name.of(firstName, lastName),
                Password.of(hashedPassword),
                Email.of(email),
                PhoneNumber.of(phoneNumber)
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
}
