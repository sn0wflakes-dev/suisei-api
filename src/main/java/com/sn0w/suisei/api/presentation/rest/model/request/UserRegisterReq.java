package com.sn0w.suisei.api.presentation.rest.model.request;

public record UserRegisterReq(
        String username,
        String firstName,
        String lastName,
        String email,
        String phoneNumber,
        String password
) {
}
