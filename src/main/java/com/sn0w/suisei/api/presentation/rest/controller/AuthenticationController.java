package com.sn0w.suisei.api.presentation.rest.controller;

import com.sn0w.suisei.api.app.service.ValidationService;
import com.sn0w.suisei.api.app.usecase.AuthenticationUsecase;
import com.sn0w.suisei.api.core.domain.user.User;
import com.sn0w.suisei.api.presentation.rest.mapper.UserMapper;
import com.sn0w.suisei.api.presentation.rest.model.request.UserRegisterReq;
import com.sn0w.suisei.api.presentation.rest.model.response.WebRes;
import com.sn0w.suisei.api.presentation.rest.model.response.auth.UserRegisterRes;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.OffsetDateTime;
import java.util.UUID;

@RestController
@RequestMapping("api/auth")
public class AuthenticationController {
    private final AuthenticationUsecase authenticationUsecase;
    private final ValidationService validationService;

    public AuthenticationController(
            AuthenticationUsecase authenticationUsecase,
            ValidationService validationService) {
        this.authenticationUsecase = authenticationUsecase;
        this.validationService = validationService;
    }

    @PostMapping(
            path = "/register",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<WebRes<UserRegisterRes>> registerController(
            @RequestBody UserRegisterReq request,
            HttpServletRequest http) {
        validationService.validate(request);

        User data = UserMapper.registerUserToDomain(request);
        authenticationUsecase.register(data);

        WebRes<UserRegisterRes> response = WebRes.<UserRegisterRes>builder()
                .meta(WebRes.Meta.builder()
                                .requestId(UUID.randomUUID().toString())
                                .timestamp(OffsetDateTime.now().toString())
                                .build())
                .data(UserRegisterRes.builder()
                                .message("Success add user")
                                .build())
                .path(http.getRequestURI())
                .build();

        return ResponseEntity.ok(response);
    }
}
