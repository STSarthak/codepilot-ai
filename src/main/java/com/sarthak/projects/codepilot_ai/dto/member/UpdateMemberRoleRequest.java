package com.sarthak.projects.codepilot_ai.dto.member;

import com.sarthak.projects.codepilot_ai.enums.ProjectRole;
import jakarta.validation.constraints.NotNull;

public record UpdateMemberRoleRequest(
        @NotNull
        ProjectRole role
) {
}
