package com.sn0w.suisei.api.presentation.rest.model.request;

public record UserLoginReq(
        String identifier,
        String password
) {
}
