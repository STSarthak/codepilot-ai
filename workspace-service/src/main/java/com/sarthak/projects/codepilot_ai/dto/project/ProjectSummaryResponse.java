package com.sarthak.projects.codepilot_ai.dto.project;

import com.sarthak.projects.codepilot_ai.enums.ProjectRole;

import java.time.Instant;

public record ProjectSummaryResponse(
        Long id,
        String name,
        Instant createdAt,
        Instant updatedAt,
        ProjectRole role
) {
}
