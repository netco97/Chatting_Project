package com.example.chat.handler;

import com.example.chat.dto.ChatMessageDTO;
import com.example.chat.dto.ChatRoomDTO;
import com.example.chat.service.ChatService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Slf4j
@RequiredArgsConstructor
@Component
public class WebSockChatHandler extends TextWebSocketHandler {
    private final ObjectMapper objectMapper;
    private final ChatService chatService;

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String payload = message.getPayload(); //클라이언트 메세지
        log.info("payload {}", payload);
        ChatMessageDTO chatMessageDTO = objectMapper.readValue(payload, ChatMessageDTO.class);
        ChatRoomDTO roomDTO = chatService.findRoomById(chatMessageDTO.getRoomId());
        roomDTO.handleActions(session, chatMessageDTO, chatService);
    }
}
