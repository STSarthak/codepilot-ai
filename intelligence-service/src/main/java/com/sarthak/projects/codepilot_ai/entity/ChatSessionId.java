package com.sarthak.projects.codepilot_ai.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;

@Embeddable
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@EqualsAndHashCode
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ChatSessionId implements Serializable {
    @Column(name = "project_id")
    Long projectId;

    @Column(name = "user_id")
    Long userId;
}
