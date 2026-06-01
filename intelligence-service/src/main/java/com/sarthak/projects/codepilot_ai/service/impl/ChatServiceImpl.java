package com.sarthak.projects.codepilot_ai.service.impl;

import com.sarthak.projects.codepilot_ai.dto.chat.ChatResponse;
import com.sarthak.projects.codepilot_ai.entity.ChatMessage;
import com.sarthak.projects.codepilot_ai.entity.ChatSession;
import com.sarthak.projects.codepilot_ai.entity.ChatSessionId;
import com.sarthak.projects.codepilot_ai.mapper.ChatMapper;
import com.sarthak.projects.codepilot_ai.repository.ChatMessageRepository;
import com.sarthak.projects.codepilot_ai.repository.ChatSessionRepository;
import com.sarthak.projects.codepilot_ai.security.AuthUtil;
import com.sarthak.projects.codepilot_ai.service.ChatService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ChatServiceImpl implements ChatService {

    private final ChatMessageRepository chatMessageRepository;
    private final ChatSessionRepository chatSessionRepository;
    private final AuthUtil authUtil;
    private final ChatMapper chatMapper;

    @Override
    public List<ChatResponse> getProjectChatHistory(Long projectId) {
        Long userId = authUtil.getCurrentUserId();

        ChatSession chatSession = chatSessionRepository.getReferenceById(
                new ChatSessionId(projectId, userId)
        );

        List<ChatMessage> chatMessageList = chatMessageRepository.findByChatSession(chatSession);

        return chatMapper.fromListOfChatMessage(chatMessageList);
    }
}
