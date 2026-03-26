package com.sn0w.suisei.api.adapter.inbound.rest.model.response.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GetUserRes {
    private String id;
    private String username;
    private String fullName;
    private String email;
    private String phoneNumber;
}
