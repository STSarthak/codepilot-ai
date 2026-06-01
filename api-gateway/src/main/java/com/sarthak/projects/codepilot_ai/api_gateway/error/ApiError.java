package com.sarthak.projects.codepilot_ai.api_gateway.error;

import org.springframework.http.HttpStatus;

import java.time.Instant;

public record ApiError(
        Instant timestamp,
        int status,
        String error,
        String message
) {
    public ApiError(HttpStatus status, String message) {
        this(Instant.now(), status.value(), status.getReasonPhrase(), message);
    }
}
