package com.sn0w.suisei.api.application.port.inbound.command;

import com.sn0w.suisei.api.core.domain.shared.Timestamp;
import com.sn0w.suisei.api.core.domain.user.Username;
import com.sn0w.suisei.api.core.domain.user.RawPassword;
import com.sn0w.suisei.api.core.domain.user.Name;
import com.sn0w.suisei.api.core.domain.user.Email;
import com.sn0w.suisei.api.core.domain.user.PhoneNumber;

import java.util.Objects;


public record UserRegisterCommand(
        Username username,
        RawPassword rawPassword,
        Name name,
        Email email,
        PhoneNumber phoneNumber,
        Timestamp timestamp
) {
    public UserRegisterCommand {
        Objects.requireNonNull(username);
        Objects.requireNonNull(rawPassword);
        Objects.requireNonNull(name);
        Objects.requireNonNull(email);
        Objects.requireNonNull(phoneNumber);
        Objects.requireNonNull(timestamp);
    }
}
