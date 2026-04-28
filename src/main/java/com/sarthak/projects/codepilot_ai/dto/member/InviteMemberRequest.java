package com.sarthak.projects.codepilot_ai.dto.member;

import com.sarthak.projects.codepilot_ai.enums.ProjectRole;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record InviteMemberRequest(

        @Email
        @NotBlank
        String email,
        @NotNull
        ProjectRole role
) {
}
