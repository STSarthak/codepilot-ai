package com.sarthak.projects.codepilot_ai.dto.member;

import com.sarthak.projects.codepilot_ai.enums.ProjectRole;

public record UpdateMemberRoleRequest(
        ProjectRole role
) {
}
