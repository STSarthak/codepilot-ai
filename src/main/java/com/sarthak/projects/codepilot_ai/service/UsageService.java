package com.sarthak.projects.codepilot_ai.service;

import com.sarthak.projects.codepilot_ai.dto.subscription.PlanLimitResponse;
import com.sarthak.projects.codepilot_ai.dto.subscription.UsageTodayResponse;

public interface UsageService {
    UsageTodayResponse getTodayUsageOfUser();

    PlanLimitResponse getCurrentSubscriptionLimitsOfUser();

    void recordTokenUsage(Long userId, int actualTokens);

    void checkDailyTokensUsage();
}
