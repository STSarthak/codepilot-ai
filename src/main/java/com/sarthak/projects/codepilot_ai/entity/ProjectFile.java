package com.sarthak.projects.codepilot_ai.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.time.Instant;

@Getter
@Setter
@FieldDefaults(level = lombok.AccessLevel.PRIVATE)
public class ProjectFile {
    Long id;
    Project project;
    String path;
    String minioObjectKey;
    User createdBy;
    User updatedBy;
    Instant createdAt;
    Instant updatedAt;
}
