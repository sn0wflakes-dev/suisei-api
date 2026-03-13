package com.sn0w.suisei.api.core.domain.user;

public class User {
    private final UserId userId;
    private final Username username;
    private final Name name;
    private final HashedPassword hashedPassword;
    private final Email email;
    private final PhoneNumber phoneNumber;

    private User(
            UserId userId,
            Username username,
            Name name,
            HashedPassword hashedPassword,
            Email email,
            PhoneNumber phoneNumber) {
        this.userId = userId;
        this.username = username;
        this.name = name;
        this.hashedPassword = hashedPassword;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public static User of(
            String username,
            String hashedPassword,
            String firstName,
            String lastName,
            String email,
            String phoneNumber
    ) {
        return new User(
                UserId.generateId(),
                Username.of(username),
                Name.of(firstName, lastName),
                HashedPassword.of(hashedPassword),
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

    public HashedPassword getHashedPassword() {
        return hashedPassword;
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
