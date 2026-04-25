package com.sarthak.projects.codepilot_ai.service.impl;

import com.sarthak.projects.codepilot_ai.dto.subscription.CheckoutRequest;
import com.sarthak.projects.codepilot_ai.dto.subscription.CheckoutResponse;
import com.sarthak.projects.codepilot_ai.dto.subscription.PortalResponse;
import com.sarthak.projects.codepilot_ai.dto.subscription.SubscriptionResponse;
import com.sarthak.projects.codepilot_ai.service.SubscriptionService;
import org.springframework.stereotype.Service;

@Service
public class SubscriptionServiceImpl implements SubscriptionService {
    @Override
    public SubscriptionResponse getCurrentSubscription(Long userId) {
        return null;
    }

    @Override
    public CheckoutResponse createCheckoutSession(CheckoutRequest request, Long userId) {
        return null;
    }

    @Override
    public PortalResponse openCustomerPortal(Long userId) {
        return null;
    }
}
