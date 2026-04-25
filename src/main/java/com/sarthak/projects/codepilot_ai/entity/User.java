package com.sarthak.projects.codepilot_ai.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "users")
public class User {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String name;
    String email;
    String password;

    String avatarUrl;

    @CreationTimestamp
    String createdAt;

    @UpdateTimestamp
    String updatedAt;

    String deletedAt;
}
