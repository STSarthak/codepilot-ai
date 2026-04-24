package com.sarthak.projects.codepilot_ai.service;

import com.sarthak.projects.codepilot_ai.dto.subscription.CheckoutRequest;
import com.sarthak.projects.codepilot_ai.dto.subscription.CheckoutResponse;
import com.sarthak.projects.codepilot_ai.dto.subscription.PortalResponse;
import com.sarthak.projects.codepilot_ai.dto.subscription.SubscriptionResponse;

public interface SubscriptionService {
    SubscriptionResponse getCurrentSubscription(Long userId);

    CheckoutResponse createCheckoutSession(CheckoutRequest request, Long userId);

    PortalResponse openCustomerPortal(Long userId);
}
