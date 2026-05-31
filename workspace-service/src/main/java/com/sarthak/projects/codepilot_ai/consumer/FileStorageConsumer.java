package com.sarthak.projects.codepilot_ai.consumer;

import com.sarthak.projects.codepilot_ai.entity.ProcessedEvent;
import com.sarthak.projects.codepilot_ai.event.FileStoreRequestEvent;
import com.sarthak.projects.codepilot_ai.event.FileStoreResponseEvent;
import com.sarthak.projects.codepilot_ai.repository.ProcessedEventRepository;
import com.sarthak.projects.codepilot_ai.service.FileService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@Slf4j
@RequiredArgsConstructor
public class FileStorageConsumer {

    private final FileService fileService;
    private final ProcessedEventRepository processedEventRepository;
    private final KafkaTemplate<String, Object> kafkaTemplate;

    @Transactional
    @KafkaListener(topics = "file-storage-request-event", groupId = "workspace-group")
    public void consumeFileEvent(FileStoreRequestEvent requestEvent) {
        if (processedEventRepository.existsById(requestEvent.sagaId())) {
            log.info("Duplicate file storage saga detected: {}", requestEvent.sagaId());
            sendResponse(requestEvent, true, null);
            return;
        }

        try {
            fileService.saveFile(requestEvent.projectId(), requestEvent.filePath(), requestEvent.content());
            processedEventRepository.save(new ProcessedEvent(requestEvent.sagaId(), LocalDateTime.now()));
            sendResponse(requestEvent, true, null);
        } catch (Exception e) {
            log.error("Failed to save file from saga {}", requestEvent.sagaId(), e);
            sendResponse(requestEvent, false, e.getMessage());
        }
    }

    private void sendResponse(FileStoreRequestEvent requestEvent, boolean success, String error) {
        FileStoreResponseEvent response = FileStoreResponseEvent.builder()
                .sagaId(requestEvent.sagaId())
                .projectId(requestEvent.projectId())
                .success(success)
                .errorMessage(error)
                .build();
        kafkaTemplate.send("file-store-responses", response);
    }
}
