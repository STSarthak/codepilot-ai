package com.sarthak.projects.codepilot_ai.service.impl;

import com.sarthak.projects.codepilot_ai.dto.subscription.PlanLimitResponse;
import com.sarthak.projects.codepilot_ai.dto.subscription.PlanResponse;
import com.sarthak.projects.codepilot_ai.dto.subscription.SubscriptionResponse;
import com.sarthak.projects.codepilot_ai.dto.subscription.UsageTodayResponse;
import com.sarthak.projects.codepilot_ai.entity.UsageLog;
import com.sarthak.projects.codepilot_ai.repository.UsageLogRepository;
import com.sarthak.projects.codepilot_ai.security.AuthUtil;
import com.sarthak.projects.codepilot_ai.service.SubscriptionService;
import com.sarthak.projects.codepilot_ai.service.UsageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class UsageServiceImpl implements UsageService {

    private final UsageLogRepository usageLogRepository;
    private final AuthUtil authUtil;
    private final SubscriptionService subscriptionService;

    @Override
    public UsageTodayResponse getTodayUsageOfUser() {
        Long userId = authUtil.getCurrentUserId();
        UsageLog todayLog = usageLogRepository.findByUserIdAndDate(userId, LocalDate.now())
                .orElseGet(() -> createNewDailyLog(userId, LocalDate.now()));
        SubscriptionResponse subscriptionResponse = subscriptionService.getCurrentSubscription();
        PlanResponse plan = subscriptionResponse.plan();

        return new UsageTodayResponse(
                todayLog.getTokensUsed(),
                plan.maxTokensPerDay(),
                0,
                plan.maxProjects()
        );
    }

    @Override
    public PlanLimitResponse getCurrentSubscriptionLimitsOfUser() {
        Long userId = authUtil.getCurrentUserId();
        PlanResponse plan = subscriptionService.getCurrentSubscription().plan();
        return new PlanLimitResponse(
                plan.name(),
                plan.maxTokensPerDay(),
                plan.maxProjects(),
                plan.unlimitedAi()
        );
    }

    @Override
    public void recordTokenUsage(Long userId, int actualTokens) {
        UsageLog todayLog = usageLogRepository.findByUserIdAndDate(userId, LocalDate.now())
                .orElseGet(() -> createNewDailyLog(userId, LocalDate.now()));

        todayLog.setTokensUsed(todayLog.getTokensUsed() + actualTokens);
        usageLogRepository.save(todayLog);
    }

    @Override
    public void checkDailyTokensUsage() {
        Long userId = authUtil.getCurrentUserId();
        PlanResponse plan = subscriptionService.getCurrentSubscription().plan();

        if (plan.unlimitedAi()) {
            return;
        }

        UsageLog todayLog = usageLogRepository.findByUserIdAndDate(userId, LocalDate.now())
                .orElseGet(() -> createNewDailyLog(userId, LocalDate.now()));

        if (todayLog.getTokensUsed() >= plan.maxTokensPerDay()) {
            throw new ResponseStatusException(HttpStatus.TOO_MANY_REQUESTS, "Daily limit reached, Upgrade now");
        }
    }

    private UsageLog createNewDailyLog(Long userId, LocalDate date) {
        UsageLog newLog = UsageLog.builder()
                .userId(userId)
                .date(date)
                .tokensUsed(0)
                .build();
        return usageLogRepository.save(newLog);
    }
}
