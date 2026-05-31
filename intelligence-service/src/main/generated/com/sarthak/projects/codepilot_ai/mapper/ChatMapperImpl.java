package com.sarthak.projects.codepilot_ai.mapper;

import com.sarthak.projects.codepilot_ai.dto.chat.ChatEventResponse;
import com.sarthak.projects.codepilot_ai.dto.chat.ChatResponse;
import com.sarthak.projects.codepilot_ai.entity.ChatEvent;
import com.sarthak.projects.codepilot_ai.entity.ChatMessage;
import com.sarthak.projects.codepilot_ai.enums.ChatEventType;
import com.sarthak.projects.codepilot_ai.enums.MessageRole;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-05-29T03:53:52+0530",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21.0.8 (Oracle Corporation)"
)
@Component
public class ChatMapperImpl implements ChatMapper {

    @Override
    public List<ChatResponse> fromListOfChatMessage(List<ChatMessage> chatMessageList) {
        if ( chatMessageList == null ) {
            return null;
        }

        List<ChatResponse> list = new ArrayList<ChatResponse>( chatMessageList.size() );
        for ( ChatMessage chatMessage : chatMessageList ) {
            list.add( chatMessageToChatResponse( chatMessage ) );
        }

        return list;
    }

    protected ChatEventResponse chatEventToChatEventResponse(ChatEvent chatEvent) {
        if ( chatEvent == null ) {
            return null;
        }

        Long id = null;
        ChatEventType type = null;
        Integer sequenceOrder = null;
        String content = null;
        String filePath = null;
        String metadata = null;

        id = chatEvent.getId();
        type = chatEvent.getType();
        sequenceOrder = chatEvent.getSequenceOrder();
        content = chatEvent.getContent();
        filePath = chatEvent.getFilePath();
        metadata = chatEvent.getMetadata();

        ChatEventResponse chatEventResponse = new ChatEventResponse( id, type, sequenceOrder, content, filePath, metadata );

        return chatEventResponse;
    }

    protected List<ChatEventResponse> chatEventListToChatEventResponseList(List<ChatEvent> list) {
        if ( list == null ) {
            return null;
        }

        List<ChatEventResponse> list1 = new ArrayList<ChatEventResponse>( list.size() );
        for ( ChatEvent chatEvent : list ) {
            list1.add( chatEventToChatEventResponse( chatEvent ) );
        }

        return list1;
    }

    protected ChatResponse chatMessageToChatResponse(ChatMessage chatMessage) {
        if ( chatMessage == null ) {
            return null;
        }

        Long id = null;
        MessageRole role = null;
        List<ChatEventResponse> events = null;
        String content = null;
        Integer tokensUsed = null;
        Instant createdAt = null;

        id = chatMessage.getId();
        role = chatMessage.getRole();
        events = chatEventListToChatEventResponseList( chatMessage.getEvents() );
        content = chatMessage.getContent();
        tokensUsed = chatMessage.getTokensUsed();
        createdAt = chatMessage.getCreatedAt();

        ChatResponse chatResponse = new ChatResponse( id, role, events, content, tokensUsed, createdAt );

        return chatResponse;
    }
}
