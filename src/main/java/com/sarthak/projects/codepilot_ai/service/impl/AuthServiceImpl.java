package com.sarthak.projects.codepilot_ai.service.impl;

import com.sarthak.projects.codepilot_ai.dto.auth.AuthResponse;
import com.sarthak.projects.codepilot_ai.dto.auth.LoginRequest;
import com.sarthak.projects.codepilot_ai.dto.auth.SignupRequest;
import com.sarthak.projects.codepilot_ai.entity.User;
import com.sarthak.projects.codepilot_ai.error.BadRequestException;
import com.sarthak.projects.codepilot_ai.mapper.UserMapper;
import com.sarthak.projects.codepilot_ai.repository.UserRepository;
import com.sarthak.projects.codepilot_ai.service.AuthService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class AuthServiceImpl implements AuthService {

    UserRepository userRepository;
    UserMapper userMapper;
    PasswordEncoder passwordEncoder;

    @Override
    public AuthResponse signup(SignupRequest request) {

        userRepository.findByUsername(request.username()).ifPresent(user -> {
            throw new BadRequestException("User already exists with username " + user.getUsername() + ".");
        });

        User user = userMapper.toEntity(request);
        user.setPassword(passwordEncoder.encode(request.password()));
        userRepository.save(user);

        return new AuthResponse("dummy", userMapper.toUserProfileResponse(user) );
    }

    @Override
    public AuthResponse login(LoginRequest request) {
        return null;
    }
}
