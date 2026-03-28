package com.sn0w.suisei.api.core.domain.event;

import com.sn0w.suisei.api.core.domain.user.Email;
import com.sn0w.suisei.api.core.domain.user.Name;
import com.sn0w.suisei.api.core.domain.user.Username;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;

public class UserRegisteredEvent extends Event {

    private static final DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd MMMM yyyy");

    private final Username username;
    private final Name name;
    private final Email email;
    private final String registeredAt;

    public static UserRegisteredEvent invoke(
            String username,
            String firstName,
            String lastName,
            String email,
            OffsetDateTime time
    ) {
        return new UserRegisteredEvent(
                Username.of(username),
                Name.of(firstName, lastName),
                Email.of(email),
                time.format(fmt)
        );
    }

    private UserRegisteredEvent(
            Username username,
            Name name,
            Email email,
            String registeredAt) {
        super();
        this.username = username;
        this.name = name;
        this.email = email;
        this.registeredAt = registeredAt;
    }

    public Username getUsername() {
        return username;
    }

    public Name getName() {
        return name;
    }

    public Email getEmail() {
        return email;
    }

    public String getRegisteredAt() {
        return registeredAt;
    }
}
