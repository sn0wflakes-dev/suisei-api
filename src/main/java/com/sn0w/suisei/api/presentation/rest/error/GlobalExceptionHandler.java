package com.sn0w.suisei.api.presentation.rest.error;

import com.sn0w.suisei.api.core.exception.user.EmailAlreadyExist;
import com.sn0w.suisei.api.core.exception.user.PhoneNumberAlreadyExist;
import com.sn0w.suisei.api.core.exception.user.UsernameAlreadyExist;
import com.sn0w.suisei.api.presentation.rest.model.response.WebRes;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolationException;
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
