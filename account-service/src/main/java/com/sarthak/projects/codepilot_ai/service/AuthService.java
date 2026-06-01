package com.sarthak.projects.codepilot_ai.service;

import com.sarthak.projects.codepilot_ai.dto.auth.AuthResponse;
import com.sarthak.projects.codepilot_ai.dto.auth.LoginRequest;
import com.sarthak.projects.codepilot_ai.dto.auth.SignupRequest;

public interface AuthService {
    AuthResponse signup(SignupRequest request);

    AuthResponse login(LoginRequest request);
}
