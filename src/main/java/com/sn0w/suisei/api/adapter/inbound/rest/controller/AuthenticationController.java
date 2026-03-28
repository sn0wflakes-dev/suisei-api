package com.sn0w.suisei.api.adapter.inbound.rest.controller;

import com.sn0w.suisei.api.application.port.inbound.command.UserRegisterCommand;
import com.sn0w.suisei.api.application.service.ValidationService;
import com.sn0w.suisei.api.application.port.inbound.AuthenticationUsecase;
import com.sn0w.suisei.api.core.domain.user.User;
import com.sn0w.suisei.api.infra.security.jwt.Jwt;
import com.sn0w.suisei.api.adapter.inbound.rest.mapper.UserMapper;
import com.sn0w.suisei.api.adapter.inbound.rest.model.request.UserLoginReq;
import com.sn0w.suisei.api.adapter.inbound.rest.model.request.UserRegisterReq;
import com.sn0w.suisei.api.adapter.inbound.rest.model.response.WebRes;
import com.sn0w.suisei.api.adapter.inbound.rest.model.response.auth.UserLoginRes;
import com.sn0w.suisei.api.adapter.inbound.rest.model.response.auth.UserRegisterRes;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.OffsetDateTime;
import java.util.UUID;

@RestController
@RequestMapping("api/auth")
public class AuthenticationController {

    private final static Logger log = LogManager.getLogger(AuthenticationController.class);

    private final AuthenticationUsecase authenticationUsecase;
    private final UserDetailsService userDetailsService;
    private final ValidationService validationService;
    private final Jwt jwt;

    public AuthenticationController(
            AuthenticationUsecase authenticationUsecase,
            UserDetailsService userDetailsService,
            ValidationService validationService, Jwt jwt) {
        this.authenticationUsecase = authenticationUsecase;
        this.userDetailsService = userDetailsService;
        this.validationService = validationService;
        this.jwt = jwt;
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

        UserRegisterCommand data = UserMapper.registerUserToDomain(request);
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

    @PostMapping(
            path = "/login",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<WebRes<UserLoginRes>> loginController(
            @RequestBody UserLoginReq request,
            HttpServletRequest http) {
        validationService.validate(request);

        User data = authenticationUsecase.login(request.identifier(), request.password());
        UserDetails userDetails = userDetailsService.loadUserByUsername(data.getUsername().getValue());
        String token = jwt.generateToken(userDetails);


        WebRes<UserLoginRes> response = WebRes.<UserLoginRes>builder()
                .meta(WebRes.Meta.builder()
                        .requestId(UUID.randomUUID().toString())
                        .timestamp(OffsetDateTime.now().toString())
                        .build())
                .data(UserLoginRes.builder()
                        .identifier(data.getUsername().getValue())
                        .token(token)
                        .message("Login success")
                        .build())
                .path(http.getRequestURI())
                .build();

        return ResponseEntity.ok(response);
    }
}
