package com.example.chat.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChatMessageDTO {
    // 메세지 타입 : 입장, 채팅
    public enum MessageType{
        ENTER, TALK
    }
    private MessageType type; // 메세지 타입
    private String roomId; // 방번호
    private String sender; // 메세지 보낸사람
    private String message; // 메세지
}
