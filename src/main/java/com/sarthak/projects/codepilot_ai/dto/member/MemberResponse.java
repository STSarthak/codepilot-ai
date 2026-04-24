package com.sarthak.projects.codepilot_ai.dto.member;

import com.sarthak.projects.codepilot_ai.enums.ProjectRole;

import java.time.Instant;

public record MemberResponse(
        Long userId,
        String email,
        String name,
        String avatarUrl,
        ProjectRole role,
        Instant invitedAt
) {
}
