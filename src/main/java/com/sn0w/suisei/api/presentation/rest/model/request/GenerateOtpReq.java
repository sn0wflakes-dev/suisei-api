package com.sn0w.suisei.api.presentation.rest.model.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record GenerateOtpReq(
        @NotBlank
        @Pattern(
                regexp = "^[a-zA-Z0-9_-]{3,32}$",
                message = "Username must be 3-32 characters, only letters, numbers, underscore, and hyphen"
        )
        String username
) {
}
