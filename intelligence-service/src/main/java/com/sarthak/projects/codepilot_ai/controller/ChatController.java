package com.sarthak.projects.codepilot_ai.controller;

import com.sarthak.projects.codepilot_ai.dto.chat.ChatRequest;
import com.sarthak.projects.codepilot_ai.dto.chat.ChatResponse;
import com.sarthak.projects.codepilot_ai.dto.chat.StreamResponse;
import com.sarthak.projects.codepilot_ai.service.AiGenerationService;
import com.sarthak.projects.codepilot_ai.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/chat")
public class ChatController {

    private final AiGenerationService aiGenerationService;
    private final ChatService chatService;

    @PostMapping(value = "/stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<ServerSentEvent<StreamResponse>> streamChat(
            @RequestBody ChatRequest request
    ) {
        return aiGenerationService.streamResponse(request.message(), request.projectId())
                .map(data -> ServerSentEvent.<StreamResponse>builder()
                        .data(data)
                        .build()
                );
    }

    @GetMapping("/projects/{projectId}")
    public ResponseEntity<List<ChatResponse>> getChatHistory(@PathVariable Long projectId) {
        return ResponseEntity.ok(chatService.getProjectChatHistory(projectId));
    }
}
