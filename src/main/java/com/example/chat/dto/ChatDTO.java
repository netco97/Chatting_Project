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
    private String msg; // 메세지

    private String kakaoId; // kakaoId;

    private String date; // date

    private int isPro; // isPro


    @Builder
    public ChatDTO(Long id, String roomId, String msg, String kakaoId, String date,String sender, String isPro){
        this.id = id;
        this.roomId = roomId;
        this.msg = msg;
        this.kakaoId = kakaoId;
        this.date = date;
        this.sender = sender;
        this.isPro = Integer.parseInt(isPro);
    }
}
