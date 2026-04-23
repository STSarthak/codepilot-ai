package com.sarthak.projects.codepilot_ai.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.time.Instant;

@Getter
@Setter
@FieldDefaults(level = lombok.AccessLevel.PRIVATE)
public class Preview {
    Long id;
    Project project;
    String namespace;
    String podName;
    String previewUrl;

    Instant startedAt;
    Instant endedAt;
    Instant createdAt;
}
