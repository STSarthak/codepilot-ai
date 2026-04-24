package com.sarthak.projects.codepilot_ai.service;

import com.sarthak.projects.codepilot_ai.dto.subscription.PlanResponse;
import org.jspecify.annotations.Nullable;

public interface PlanService {
    PlanResponse getAllActivePlans();
}
