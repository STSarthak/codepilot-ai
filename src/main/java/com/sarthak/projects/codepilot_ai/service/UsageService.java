package com.sarthak.projects.codepilot_ai.service;

import com.sarthak.projects.codepilot_ai.dto.subscription.PlanLimitResponse;
import com.sarthak.projects.codepilot_ai.dto.subscription.UsageTodayResponse;
import org.jspecify.annotations.Nullable;

public interface UsageService {
    UsageTodayResponse getTodayUsageOfUser(Long userId);

    PlanLimitResponse getCurrentSubscriptionLimitsOfUser(Long userId);
}
