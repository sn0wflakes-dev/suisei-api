package com.sn0w.suisei.api.adapter.inbound.rest.model.response.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserLoginRes {
    private String identifier;
    private String token;
    private String message;
}
