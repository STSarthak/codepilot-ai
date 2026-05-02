package com.sarthak.projects.codepilot_ai.dto.chat;

public record ChatRequest(
    String message,
    Long projectId
) {
}
