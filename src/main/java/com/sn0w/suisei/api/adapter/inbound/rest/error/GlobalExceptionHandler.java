package com.sn0w.suisei.api.adapter.inbound.rest.error;

import com.sn0w.suisei.api.core.exception.otp.OtpExpire;
import com.sn0w.suisei.api.core.exception.otp.OtpNotMatch;
import com.sn0w.suisei.api.core.exception.user.EmailAlreadyExist;
import com.sn0w.suisei.api.core.exception.user.InvalidCredential;
import com.sn0w.suisei.api.core.exception.user.PhoneNumberAlreadyExist;
import com.sn0w.suisei.api.core.exception.user.UsernameAlreadyExist;
import com.sn0w.suisei.api.adapter.inbound.rest.model.response.WebRes;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolationException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.OffsetDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private final static Logger log = LogManager.getLogger(GlobalExceptionHandler.class);

    // Domain Exception
    @ExceptionHandler(EmailAlreadyExist.class)
    public ResponseEntity<WebRes<String>> emailAlreadyExistEx(EmailAlreadyExist ex, HttpServletRequest http) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(WebRes.<String>builder()
                        .meta(WebRes.Meta.builder()
                                .requestId(UUID.randomUUID().toString())
                                .timestamp(OffsetDateTime.now().toString())
                                .build())
                        .error(WebRes.Error.builder()
                                .errorCode(ex.getCode())
                                .message(ex.getMessage())
                                .build())
                        .path(http.getRequestURI())
                        .build());
    }

    // Domain Exception
    @ExceptionHandler(PhoneNumberAlreadyExist.class)
    public ResponseEntity<WebRes<String>> phoneAlreadyExistEx(PhoneNumberAlreadyExist ex, HttpServletRequest http) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(WebRes.<String>builder()
                        .meta(WebRes.Meta.builder()
                                .requestId(UUID.randomUUID().toString())
                                .timestamp(OffsetDateTime.now().toString())
                                .build())
                        .error(WebRes.Error.builder()
                                .errorCode(ex.getCode())
                                .message(ex.getMessage())
                                .build())
                        .path(http.getRequestURI())
                        .build());
    }

    // Domain Exception
    @ExceptionHandler(UsernameAlreadyExist.class)
    public ResponseEntity<WebRes<String>> usernameAlreadyExistEx(UsernameAlreadyExist ex, HttpServletRequest http) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(WebRes.<String>builder()
                        .meta(WebRes.Meta.builder()
                                .requestId(UUID.randomUUID().toString())
                                .timestamp(OffsetDateTime.now().toString())
                                .build())
                        .error(WebRes.Error.builder()
                                .errorCode(ex.getCode())
                                .message(ex.getMessage())
                                .build())
                        .path(http.getRequestURI())
                        .build());
    }

    // Domain Exception
    @ExceptionHandler(InvalidCredential.class)
    public ResponseEntity<WebRes<String>> invalidCredentialEx(InvalidCredential ex, HttpServletRequest http) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(WebRes.<String>builder()
                        .meta(WebRes.Meta.builder()
                                .requestId(UUID.randomUUID().toString())
                                .timestamp(OffsetDateTime.now().toString())
                                .build())
                        .error(WebRes.Error.builder()
                                .errorCode(ex.getCode())
                                .message(ex.getMessage())
                                .build())
                        .path(http.getRequestURI())
                        .build());
    }

    // Domain Exception
    @ExceptionHandler(OtpExpire.class)
    public ResponseEntity<WebRes<String>> otpExpireEx(OtpExpire ex, HttpServletRequest http) {
        return ResponseEntity.status(HttpStatus.GONE)
                .body(WebRes.<String>builder()
                        .meta(WebRes.Meta.builder()
                                .requestId(UUID.randomUUID().toString())
                                .timestamp(OffsetDateTime.now().toString())
                                .build())
                        .error(WebRes.Error.builder()
                                .errorCode(ex.getCode())
                                .message(ex.getMessage())
                                .build())
                        .path(http.getRequestURI())
                        .build());
    }

    // Domain Exception
    @ExceptionHandler(OtpNotMatch.class)
    public ResponseEntity<WebRes<String>> otpNotMatchEx(OtpNotMatch ex, HttpServletRequest http) {
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_CONTENT)
                .body(WebRes.<String>builder()
                        .meta(WebRes.Meta.builder()
                                .requestId(UUID.randomUUID().toString())
                                .timestamp(OffsetDateTime.now().toString())
                                .build())
                        .error(WebRes.Error.builder()
                                .errorCode(ex.getCode())
                                .message(ex.getMessage())
                                .build())
                        .path(http.getRequestURI())
                        .build());
    }

    // Validation
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<WebRes<String>> validationEx(ConstraintViolationException ex, HttpServletRequest http) {
        List<Map<String, String>> violationList = ex.getConstraintViolations().stream()
                .map(constraintViolation -> {
                    Map<String, String> errorList = new HashMap<>();
                    errorList.put("Field", constraintViolation.getPropertyPath().toString());
                    errorList.put("Message", constraintViolation.getMessage());
                    return errorList;
                }).toList();


        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(WebRes.<String>builder()
                        .meta(WebRes.Meta.builder()
                                .requestId(UUID.randomUUID().toString())
                                .timestamp(OffsetDateTime.now().toString())
                                .build())
                        .error(WebRes.Error.builder()
                                .errorCode("VALIDATION_ERROR")
                                .message("Validation error")
                                .detail(violationList)
                                .build())
                        .path(http.getRequestURI())
                        .build());

    }

    // Generic Exception
    @ExceptionHandler(Exception.class)
    public ResponseEntity<WebRes<String>> genericEx(HttpServletRequest http) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(WebRes.<String>builder()
                        .meta(WebRes.Meta.builder()
                                .requestId(UUID.randomUUID().toString())
                                .timestamp(OffsetDateTime.now().toString())
                                .build())
                        .error(WebRes.Error.builder()
                                .errorCode("INTERNAL_SERVER_ERROR")
                                .message("Internal server error")
                                .build())
                        .path(http.getRequestURI())
                        .build());
    }
}
