package com.sn0w.suisei.api.adapter.inbound.rest.model.request;

import jakarta.validation.constraints.Pattern;
import org.apache.logging.log4j.core.config.plugins.validation.constraints.NotBlank;
import org.jspecify.annotations.NonNull;

public record UserLoginReq(
        @NotBlank(message = "Username or Email is required")
        String identifier,

        @NotBlank(message = "Password is required")
        String password
) {
}
