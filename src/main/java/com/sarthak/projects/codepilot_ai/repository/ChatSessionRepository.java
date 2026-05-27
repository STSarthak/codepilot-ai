package com.sarthak.projects.codepilot_ai.repository;

import com.sarthak.projects.codepilot_ai.entity.ChatSession;
import com.sarthak.projects.codepilot_ai.entity.ChatSessionId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatSessionRepository extends JpaRepository<ChatSession, ChatSessionId> {
}
