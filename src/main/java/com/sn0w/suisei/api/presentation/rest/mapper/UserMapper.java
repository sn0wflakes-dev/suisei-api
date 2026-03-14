package com.sn0w.suisei.api.presentation.rest.mapper;

import com.sn0w.suisei.api.core.domain.user.User;
import com.sn0w.suisei.api.presentation.rest.model.request.UserRegisterReq;

public class UserMapper {
    public static User registerUserToDomain(UserRegisterReq dto) {
        return User.of(
                dto.username(),
                dto.password(),
                dto.firstName(),
                dto.lastName(),
                dto.email(),
                dto.phoneNumber()
        );
    }
}
