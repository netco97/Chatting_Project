package com.example.chat.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ChatDTO {
    private String roomId; // 방번호
    private String sender; // 메세지 보낸사람
    private String message; // 메세지

    private String isProType;


    @Builder
    public ChatDTO(String roomId, String sender, String message){
        this.roomId = roomId;
        this.sender = sender;
        this.message = message;
    }
}