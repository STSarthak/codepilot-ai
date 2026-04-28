package com.sarthak.projects.codepilot_ai.dto.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record LoginRequest(
        @NotBlank
        @Email
        String email,
        @Size(min = 4)
        String password
) {
}
