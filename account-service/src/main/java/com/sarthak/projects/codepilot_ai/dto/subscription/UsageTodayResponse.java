package com.sarthak.projects.codepilot_ai.dto.subscription;

public record UsageTodayResponse(
        Integer tokenUsed,
        Integer tokenLimit,
        Integer previewsRunning,
        Integer previewsLimit
) {
}
