package com.sn0w.suisei.api.infra.security.error;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sn0w.suisei.api.presentation.rest.model.response.WebRes;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.jspecify.annotations.NonNull;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.OffsetDateTime;
import java.util.UUID;

@Component
public class JwtEntryPointHandler implements AuthenticationEntryPoint {

    private final ObjectMapper  objectMapper = new ObjectMapper();

    @Override
    public void commence(
            HttpServletRequest request,
            HttpServletResponse response,
            @NonNull AuthenticationException authException) throws IOException, ServletException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);

        WebRes<String> body = WebRes.<String>builder()
                .meta(WebRes.Meta.builder()
                        .requestId(UUID.randomUUID().toString())
                        .timestamp(OffsetDateTime.now().toString())
                        .build())
                .error(WebRes.Error.builder()
                        .errorCode("UNAUTHORIZED")
                        .message("Authentication required")
                        .build())
                .path(request.getRequestURI())
                .build();

        response.getWriter().write(objectMapper.writeValueAsString(body));
    }
}
