package com.sarthak.projects.codepilot_ai.consumer;

import com.sarthak.projects.codepilot_ai.enums.ChatEventStatus;
import com.sarthak.projects.codepilot_ai.event.FileStoreResponseEvent;
import com.sarthak.projects.codepilot_ai.repository.ChatEventRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
public class IntelligenceSagaResponseHandler {

    private final ChatEventRepository chatEventRepository;

    @Transactional
    @KafkaListener(topics = "file-store-responses", groupId = "intelligence-group")
    public void handleSagaResponse(FileStoreResponseEvent response) {
        chatEventRepository.findBySagaId(response.sagaId()).ifPresent(event -> {
            if (!ChatEventStatus.PENDING.equals(event.getStatus())) {
                return;
            }

            event.setStatus(response.success() ? ChatEventStatus.CONFIRMED : ChatEventStatus.FAILED);
        });
    }
}
