package com.sarthak.projects.codepilot_ai.dto.chat;

import com.sarthak.projects.codepilot_ai.enums.MessageRole;

import java.time.Instant;
import java.util.List;

public record ChatResponse(
        Long id,
        MessageRole role,
        List<ChatEventResponse> events,
        String content,
        Integer tokensUsed,
        Instant createdAt
) {
}
