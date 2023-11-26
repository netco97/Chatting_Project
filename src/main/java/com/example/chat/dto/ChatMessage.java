package com.example.chat.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class ChatMessage {
    // 메세지 타입 : 입장, 채팅
    public enum MessageType{
        ENTER, TALK
    }
    private MessageType type; // 메세지 타입
    private String roomId; // 방번호
    private String message; // 메세지
    private String kakaoId; // 카카오 ID;
    private String date; // date

    private String isPro; //ispro
    @Builder
    public ChatMessage(String roomId, String message,String kakaoId,String date, String isPro){
        this.roomId = roomId;
        this.message = message;
        this.kakaoId = kakaoId;
        this.date = date;
        this.isPro = isPro;
    }
}
