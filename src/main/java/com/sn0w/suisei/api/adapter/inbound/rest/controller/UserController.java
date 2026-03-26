package com.sn0w.suisei.api.adapter.inbound.rest.controller;

import com.sn0w.suisei.api.application.service.UserService;
import com.sn0w.suisei.api.application.service.ValidationService;
import com.sn0w.suisei.api.core.domain.user.User;
import com.sn0w.suisei.api.adapter.inbound.rest.model.response.WebRes;
import com.sn0w.suisei.api.adapter.inbound.rest.model.response.user.GetUserRes;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.OffsetDateTime;
import java.util.UUID;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final static Logger log = LogManager.getLogger(UserController.class);

    private final UserService userService;
    private final ValidationService validationService;

    public UserController(
            UserService userService,
            ValidationService validationService) {
        this.userService = userService;
        this.validationService = validationService;
    }

    @GetMapping(
            path = "/{username}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<WebRes<GetUserRes>> loginController(
            @NotBlank
            @Pattern(regexp = "^[a-zA-Z0-9_-]{3,32}$", message = "Username must be 3-32 characters, only letters, numbers, underscore, and hyphen")
            @PathVariable String username,
            HttpServletRequest http) {
        validationService.validate(username);

        User data = userService.getUserByUsername(username);

        WebRes<GetUserRes> response = WebRes.<GetUserRes>builder()
                .meta(WebRes.Meta.builder()
                        .requestId(UUID.randomUUID().toString())
                        .timestamp(OffsetDateTime.now().toString())
                        .build())
                .data(GetUserRes.builder()
                        .id(data.getUserId().getValue())
                        .username(data.getUsername().getValue())
                        .fullName(data.getName().getFullName())
                        .email(data.getEmail().getValue())
                        .phoneNumber(data.getPhoneNumber().getValue())
                        .build())
                .path(http.getRequestURI())
                .build();

        return ResponseEntity.ok(response);
    }
}
