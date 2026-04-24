package com.sarthak.projects.codepilot_ai.entity;

import com.sarthak.projects.codepilot_ai.enums.MessageRole;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.time.Instant;

@Getter
@Setter
@FieldDefaults(level = lombok.AccessLevel.PRIVATE)
public class ChatMessage {
    Long id;
    ChatSession chatSession;
    String content;
    MessageRole role;
    String toolCalls;
    Integer tokenUsed;
    Instant createdAt;
}
