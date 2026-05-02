package com.sarthak.projects.codepilot_ai.mapper;

import com.sarthak.projects.codepilot_ai.dto.auth.SignupRequest;
import com.sarthak.projects.codepilot_ai.dto.auth.UserProfileResponse;
import com.sarthak.projects.codepilot_ai.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User toEntity(SignupRequest signupRequest);

    UserProfileResponse toUserProfileResponse(User user);
}
