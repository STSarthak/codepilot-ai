package com.sarthak.projects.codepilot_ai.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ChatSessionId implements Serializable {
    Long projectId;
    Long userId;
}
