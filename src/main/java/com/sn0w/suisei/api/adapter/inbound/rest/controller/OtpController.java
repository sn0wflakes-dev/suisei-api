package com.sn0w.suisei.api.adapter.inbound.rest.controller;

import com.sn0w.suisei.api.application.service.ValidationService;
import com.sn0w.suisei.api.application.port.inbound.OtpUsecase;
import com.sn0w.suisei.api.adapter.inbound.rest.model.request.GenerateOtpReq;
import com.sn0w.suisei.api.adapter.inbound.rest.model.request.VerifyOtpReq;
import com.sn0w.suisei.api.adapter.inbound.rest.model.response.WebRes;
import com.sn0w.suisei.api.adapter.inbound.rest.model.response.auth.GenerateOtpRes;
import com.sn0w.suisei.api.adapter.inbound.rest.model.response.auth.VerifyOtpRes;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.OffsetDateTime;
import java.util.UUID;

@RestController
@RequestMapping("/api/otp")
public class OtpController {

    private final OtpUsecase otpService;
    private final ValidationService validationService;

    public OtpController(
            OtpUsecase otpService,
            ValidationService validationService) {
        this.otpService = otpService;
        this.validationService = validationService;
    }

    @PostMapping("/generate")
    public ResponseEntity<WebRes<GenerateOtpRes>> generateOtpController(
            @RequestBody GenerateOtpReq request,
            HttpServletRequest http){
        validationService.validate(request);

        otpService.generateOtp(request.username());

        WebRes<GenerateOtpRes> response = WebRes.<GenerateOtpRes>builder()
                .meta(WebRes.Meta.builder()
                        .requestId(UUID.randomUUID().toString())
                        .timestamp(OffsetDateTime.now().toString())
                        .build())
                .data(GenerateOtpRes.builder()
                        .message("Success generate otp")
                        .build())
                .path(http.getRequestURI())
                .build();

        return ResponseEntity.ok(response);
    }

    @PostMapping("/verify")
    public ResponseEntity<WebRes<VerifyOtpRes>> generateOtpController(
            @RequestBody VerifyOtpReq request,
            HttpServletRequest http){
        validationService.validate(request);

        otpService.verifyOtp(request.username(), request.code());

        WebRes<VerifyOtpRes> response = WebRes.<VerifyOtpRes>builder()
                .meta(WebRes.Meta.builder()
                        .requestId(UUID.randomUUID().toString())
                        .timestamp(OffsetDateTime.now().toString())
                        .build())
                .data(VerifyOtpRes.builder()
                        .message("Success verify otp")
                        .build())
                .path(http.getRequestURI())
                .build();

        return ResponseEntity.ok(response);
    }
}
