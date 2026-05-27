package com.sarthak.projects.codepilot_ai.dto.chat;

import com.sarthak.projects.codepilot_ai.enums.ChatEventType;

public record ChatEventResponse(
        Long id,
        ChatEventType type,
        Integer sequenceOrder,
        String content,
        String filePath,
        String metadata
) {
}
