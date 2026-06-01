package com.sarthak.projects.codepilot_ai.repository;

import com.sarthak.projects.codepilot_ai.entity.ChatEvent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ChatEventRepository extends JpaRepository<ChatEvent, Long> {
    Optional<ChatEvent> findBySagaId(String sagaId);
}
