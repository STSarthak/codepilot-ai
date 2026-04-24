package com.sarthak.projects.codepilot_ai.controller;

import com.sarthak.projects.codepilot_ai.dto.subscription.*;
import com.sarthak.projects.codepilot_ai.service.PlanService;
import com.sarthak.projects.codepilot_ai.service.SubscriptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class BillingController {

    private final PlanService planService;
    private final SubscriptionService subscriptionService;

    @GetMapping("/api/plans")
    public ResponseEntity<PlanResponse> getAllPlans(){
        return ResponseEntity.ok(planService.getAllActivePlans());
    }

    @GetMapping("/api/me/subscription")
    public ResponseEntity<SubscriptionResponse> setMySubscription(){
        Long userId = 1l;
        return ResponseEntity.ok(subscriptionService.getCurrentSubscription(userId));
    }

    @PostMapping("/api/stripe/checkout")
    public ResponseEntity<CheckoutResponse> createCheckoutResponse(
            @RequestBody CheckoutRequest request
    ){
        Long userId = 1L;
        return ResponseEntity.ok(subscriptionService.createCheckoutSession(request, userId));
    }

    @PostMapping("/api/stripe/portal")
    public ResponseEntity<PortalResponse> openCustomerPortal(){
        Long userId = 1L;
        return ResponseEntity.ok(subscriptionService.openCustomerPortal(userId));
    }
}
