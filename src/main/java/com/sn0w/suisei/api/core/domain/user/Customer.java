package com.sn0w.suisei.api.core.domain.user;

import com.sn0w.suisei.api.core.domain.shared.Timestamp;

public class Customer extends User{
    private final CustomerAddress customerAddress;
    public Customer(
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
