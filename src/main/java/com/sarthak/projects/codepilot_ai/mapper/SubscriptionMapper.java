package com.sarthak.projects.codepilot_ai.mapper;

import com.sarthak.projects.codepilot_ai.dto.subscription.PlanResponse;
import com.sarthak.projects.codepilot_ai.dto.subscription.SubscriptionResponse;
import com.sarthak.projects.codepilot_ai.entity.Plan;
import com.sarthak.projects.codepilot_ai.entity.Subscription;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SubscriptionMapper {

    SubscriptionResponse toSubscriptionResponse(Subscription subscription);

    PlanResponse toPlanResponse(Plan plan);
}
