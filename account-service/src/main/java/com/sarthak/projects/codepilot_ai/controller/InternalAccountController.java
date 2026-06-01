package com.sarthak.projects.codepilot_ai.controller;

import com.sarthak.projects.codepilot_ai.dto.PlanDto;
import com.sarthak.projects.codepilot_ai.dto.UserDto;
import com.sarthak.projects.codepilot_ai.error.ResourceNotFoundException;
import com.sarthak.projects.codepilot_ai.mapper.UserMapper;
import com.sarthak.projects.codepilot_ai.repository.UserRepository;
import com.sarthak.projects.codepilot_ai.service.SubscriptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/internal/v1")
@RequiredArgsConstructor
public class InternalAccountController {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final SubscriptionService subscriptionService;

    @GetMapping("/users/{id}")
    public UserDto getUserById(@PathVariable Long id) {
        return userRepository.findById(id)
                .map(userMapper::toUserDto)
                .orElseThrow(() -> new ResourceNotFoundException("User", id.toString()));
    }

    @GetMapping("/users/by-email")
    public Optional<UserDto> getUserByEmail(@RequestParam String email) {
        return userRepository.findByUsername(email)
                .map(userMapper::toUserDto);
    }

    @GetMapping("/billing/current-plan")
    public PlanDto getCurrentSubscribedPlan() {
        return subscriptionService.getCurrentSubscribedPlanByUser();
    }
}
