package com.example.chat.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChatMessage {
    // 메세지 타입 : 입장, 채팅
    public enum MessageType{
        ENTER, TALK
    }
    private MessageType type; // 메세지 타입
    private String roomId; // 방번호
    private String sender; // 메세지 보낸사람
    private String message; // 메세지
    @Builder
    public ChatMessage(String roomId, String sender, String message){
        this.roomId = roomId;
        this.sender = sender;
        this.message = message;
    }
}
