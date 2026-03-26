package com.sn0w.suisei.api.adapter.inbound.rest.model.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record UserRegisterReq(
        @NotBlank(message = "Username is required")
        @Pattern(
                regexp = "^[a-zA-Z0-9_-]{3,32}$",
                message = "Username must be 3-32 characters, only letters, numbers, underscore, and hyphen"
        )
        String username,

        @NotBlank(message = "First name is required")
        @Pattern(
                regexp = "^[a-zA-Z ]{3,255}$",
                message = "First name must be 1-255 characters and contain only letters and spaces"
        )
        String firstName,

        @NotBlank(message = "Last name is required")
        @Pattern(
                regexp = "^[a-zA-Z ]{3,255}$",
                message = "Last name must be 1-255 characters and contain only letters and spaces"
        )
        String lastName,

        @NotBlank(message = "Email is required")
        @Email(message = "Invalid email format")
        String email,

        @NotBlank(message = "Phone number is required")
        @Pattern(
                regexp = "^[0-9]{8,17}$",
                message = "Phone number must be 8-17 digits"
        )
        String phoneNumber,

        @NotBlank(message = "Password is required")
        @Pattern(
                regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$",
                message = "Password must be at least 8 characters and contain uppercase, lowercase, number, and special character"
        )
        String password
) {
}
