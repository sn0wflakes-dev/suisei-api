package com.sn0w.suisei.api.adapter.inbound.rest.model.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record VerifyOtpReq(
        @NotBlank
        @Pattern(
                regexp = "^[a-zA-Z0-9_-]{3,32}$",
                message = "Username must be 3-32 characters, only letters, numbers, underscore, and hyphen"
        )
        String username,

        @NotBlank
        @Pattern(
                regexp = "^[0-9]{6}$",
                message = "Otp code must be exactly 6 digit numbers"
        )
        String code
) {
}
