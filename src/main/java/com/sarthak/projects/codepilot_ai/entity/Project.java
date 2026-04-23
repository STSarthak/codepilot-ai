package com.sarthak.projects.codepilot_ai.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = lombok.AccessLevel.PRIVATE)
public class Project {
    Long id;
    String name;
    User owner;
    Boolean isPublic;
    String createdAt;
    String updatedAt;
    String deletedAt;
}
