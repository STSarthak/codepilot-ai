package com.sarthak.projects.codepilot_ai.entity;

import com.sarthak.projects.codepilot_ai.enums.ProjectRole;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.time.Instant;

@Getter
@Setter
@FieldDefaults(level = lombok.AccessLevel.PRIVATE)
public class ProjectMember {
    ProjectMemberId id;
    Project project;
    User user;
    ProjectRole projectRole;

    Instant invtedAt;
    Instant acceptedAt;
}
