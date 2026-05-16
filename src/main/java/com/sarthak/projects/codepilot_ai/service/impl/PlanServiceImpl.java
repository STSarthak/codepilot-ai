package com.sarthak.projects.codepilot_ai.service.impl;

import com.sarthak.projects.codepilot_ai.dto.subscription.PlanResponse;
import com.sarthak.projects.codepilot_ai.service.PlanService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlanServiceImpl implements PlanService {
    @Override
    public List<PlanResponse> getAllActivePlans() {
        return List.of();
    }
}
