package com.example.chat.dto;

import com.example.chat.service.ChatService;
import lombok.Builder;
import lombok.Getter;
import org.springframework.web.socket.WebSocketSession;

import java.util.HashSet;
import java.util.Set;

@Getter
public class ChatRoomDTO {
    private String roomId;
    private String name;
    private Set<WebSocketSession> sessions = new HashSet<>(); //클라이언트 정보 리스트

    @Builder
    public ChatRoomDTO(String roomId, String name){
        this.roomId = roomId;
        this.name = name;
    }

    public void handleActions(WebSocketSession session, ChatMessageDTO chatMessageDTO, ChatService chatService){
        if(chatMessageDTO.getType().equals(ChatMessageDTO.MessageType.ENTER)){
            sessions.add(session);
            chatMessageDTO.setMessage(chatMessageDTO.getSender()+ "님이 입장했습니다."); //입장
        }
        sendMessage(chatMessageDTO,chatService); //대화하기
    }

    public <T> void sendMessage(T message, ChatService chatService){
        sessions.parallelStream().forEach(session -> chatService.sendMessage(session, message));
    }
}
