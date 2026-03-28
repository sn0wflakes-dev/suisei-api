package com.sn0w.suisei.api.adapter.inbound.rest.mapper;

import com.sn0w.suisei.api.application.port.inbound.command.UserRegisterCommand;
import com.sn0w.suisei.api.core.domain.shared.Timestamp;
import com.sn0w.suisei.api.core.domain.user.*;
import com.sn0w.suisei.api.adapter.inbound.rest.model.request.UserRegisterReq;

public class UserMapper {
    public static UserRegisterCommand registerUserToDomain(UserRegisterReq dto) {
        return new UserRegisterCommand (
                Username.of(dto.username()),
                RawPassword.of(dto.password()),
                Name.of(dto.firstName(), dto.lastName()),
                Email.of(dto.email()),
                PhoneNumber.of(dto.phoneNumber()),
                Timestamp.create()
        );
    }
}
