package com.sarthak.projects.codepilot_ai.entity;

import com.sarthak.projects.codepilot_ai.enums.ProjectRole;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.Instant;

@Getter
@Setter
@FieldDefaults(level = lombok.AccessLevel.PRIVATE)
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "project_members")
public class ProjectMember {

    @EmbeddedId
    ProjectMemberId id;

    @ManyToOne
    @MapsId("projectId")
    Project project;

    @ManyToOne
    @MapsId("userId")
    User user;

    @Column(nullable = false)
    @Enumerated
    ProjectRole projectRole;

    Instant invtedAt;
    Instant acceptedAt;
}
