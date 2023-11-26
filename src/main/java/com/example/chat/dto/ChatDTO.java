package com.example.chat.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ChatDTO {

    private Long id;
    private String roomId; // 방번호
    private String sender; // 메세지 보낸사람
    private String message; // 메세지

    private String kakaoId; // kakaoId;

    private String date; // date


    @Builder
    public ChatDTO(Long id, String roomId, String message, String kakaoId, String date){
        this.id = id;
        this.roomId = roomId;
        this.message = message;
        this.kakaoId = kakaoId;
        this.date = date;
    }
}
