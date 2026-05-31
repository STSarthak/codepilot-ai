package com.sarthak.projects.codepilot_ai.mapper;

import com.sarthak.projects.codepilot_ai.dto.PlanDto;
import com.sarthak.projects.codepilot_ai.dto.subscription.PlanResponse;
import com.sarthak.projects.codepilot_ai.dto.subscription.SubscriptionResponse;
import com.sarthak.projects.codepilot_ai.entity.Plan;
import com.sarthak.projects.codepilot_ai.entity.Subscription;
import java.time.Instant;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-05-30T00:10:54+0530",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21.0.8 (Oracle Corporation)"
)
@Component
public class SubscriptionMapperImpl implements SubscriptionMapper {

    @Override
    public SubscriptionResponse toSubscriptionResponse(Subscription subscription) {
        if ( subscription == null ) {
            return null;
        }

        PlanResponse plan = null;
        String status = null;
        Instant currentPeriodEnd = null;

        plan = toPlanResponse( subscription.getPlan() );
        if ( subscription.getStatus() != null ) {
            status = subscription.getStatus().name();
        }
        currentPeriodEnd = subscription.getCurrentPeriodEnd();

        Long tokenUsedThisCycle = null;

        SubscriptionResponse subscriptionResponse = new SubscriptionResponse( plan, status, currentPeriodEnd, tokenUsedThisCycle );

        return subscriptionResponse;
    }

    @Override
    public PlanResponse toPlanResponse(Plan plan) {
        if ( plan == null ) {
            return null;
        }

        Long id = null;
        String name = null;
        Integer maxProjects = null;
        Integer maxTokensPerDay = null;
        Boolean unlimitedAi = null;

        id = plan.getId();
        name = plan.getName();
        maxProjects = plan.getMaxProjects();
        maxTokensPerDay = plan.getMaxTokensPerDay();
        unlimitedAi = plan.getUnlimitedAi();

        String price = null;

        PlanResponse planResponse = new PlanResponse( id, name, maxProjects, maxTokensPerDay, unlimitedAi, price );

        return planResponse;
    }

    @Override
    public PlanDto toPlanDto(Plan plan) {
        if ( plan == null ) {
            return null;
        }

        Long id = null;
        String name = null;
        Integer maxProjects = null;
        Integer maxTokensPerDay = null;
        Boolean unlimitedAi = null;

        id = plan.getId();
        name = plan.getName();
        maxProjects = plan.getMaxProjects();
        maxTokensPerDay = plan.getMaxTokensPerDay();
        unlimitedAi = plan.getUnlimitedAi();

        String price = null;

        PlanDto planDto = new PlanDto( id, name, maxProjects, maxTokensPerDay, unlimitedAi, price );

        return planDto;
    }
}
