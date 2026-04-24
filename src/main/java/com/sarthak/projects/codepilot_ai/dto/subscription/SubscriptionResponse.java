package com.sarthak.projects.codepilot_ai.dto.subscription;

import com.sarthak.projects.codepilot_ai.entity.Plan;
import com.sarthak.projects.codepilot_ai.entity.User;
import com.sarthak.projects.codepilot_ai.enums.SubscriptionStatus;

import java.time.Instant;

public record SubscriptionResponse(
        PlanResponse plan,
        String status,
        Instant periodEnd,
        Long tokenUsedThisCycle
) {
}
