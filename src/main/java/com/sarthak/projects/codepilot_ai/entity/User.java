package com.sarthak.projects.codepilot_ai.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User {
    Long id;
    String name;
    String email;
    String password;
    String avatarUrl;
    String createdAt;
    String updatedAt;
    String deletedAt;
}
