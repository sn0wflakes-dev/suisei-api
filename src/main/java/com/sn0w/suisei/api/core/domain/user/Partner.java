package com.sn0w.suisei.api.core.domain.user;

import com.sn0w.suisei.api.core.domain.shared.Timestamp;

public class Partner extends User {
    private final CakeStall cakeStall;
    private final OfficeNumber officeNumber;

    public static Partner createPartner(
            String username,
            String firstName,
            String lastName,
            String password,
            String email,
            String phoneNumber,
            String cakeStall,
            String officeNumber
    ) {
        return new Partner(
                UserId.generateId(),
                Username.of(username),
                Name.of(firstName, lastName),
                RawPassword.of(password),
                Email.of(email),
                PhoneNumber.of(phoneNumber),
                Role.PARTNER,
                Timestamp.create(),
                CakeStall.of(cakeStall),
                OfficeNumber.of(officeNumber)
        );
    }

    private Partner(
            UserId userId,
            Username username,
            Name name,
            Password password,
            Email email,
            PhoneNumber phoneNumber,
            Role role,
            Timestamp timestamp,
            CakeStall cakeStall,
            OfficeNumber officeNumber) {
        super(userId, username, name, password, email, phoneNumber, role, timestamp);
        this.cakeStall = cakeStall;
        this.officeNumber = officeNumber;
    }

    public CakeStall getCakeStall() {
        return cakeStall;
    }

    public OfficeNumber getOfficeNumber() {
        return officeNumber;
    }
}
