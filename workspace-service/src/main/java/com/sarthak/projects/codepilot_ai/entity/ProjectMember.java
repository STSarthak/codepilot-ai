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

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    ProjectRole projectRole;

    Instant invitedAt;
    Instant acceptedAt;
}
