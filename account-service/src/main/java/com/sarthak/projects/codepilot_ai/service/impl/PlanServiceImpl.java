package com.sarthak.projects.codepilot_ai.service.impl;

import com.sarthak.projects.codepilot_ai.dto.subscription.PlanResponse;
import com.sarthak.projects.codepilot_ai.mapper.SubscriptionMapper;
import com.sarthak.projects.codepilot_ai.repository.PlanRepository;
import com.sarthak.projects.codepilot_ai.service.PlanService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PlanServiceImpl implements PlanService {

    private final PlanRepository planRepository;
    private final SubscriptionMapper subscriptionMapper;

    @Override
    public List<PlanResponse> getAllActivePlans() {
        return planRepository.findByActiveTrue().stream()
                .map(subscriptionMapper::toPlanResponse)
                .toList();
    }
}
