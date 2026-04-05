package com.sn0w.suisei.api.core.domain.user;

import com.sn0w.suisei.api.core.domain.shared.Timestamp;

public class Owner extends User {

    public static Owner createOwner(
            String username,
            String password,
            String firstName,
            String lastName,
            String email,
            String phoneNumber
    ) {
        return new Owner(
                UserId.generateId(),
                Username.of(username),
                Name.of(firstName, lastName),
                HashedPassword.of(password),
                Email.of(email),
                PhoneNumber.of(phoneNumber),
                Role.OWNER,
                Timestamp.create()
        );
    }

    private Owner(
            UserId userId,
            Username username,
            Name name,
            Password password,
            Email email,
            PhoneNumber phoneNumber,
            Role role,
            Timestamp timestamp) {
        super(userId, username, name, password, email, phoneNumber, role, timestamp);
    }
}
