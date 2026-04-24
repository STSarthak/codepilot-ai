package com.sarthak.projects.codepilot_ai.dto.member;

import com.sarthak.projects.codepilot_ai.enums.ProjectRole;

public record InviteMemberRequest(
        String email,
        ProjectRole role
) {
}
