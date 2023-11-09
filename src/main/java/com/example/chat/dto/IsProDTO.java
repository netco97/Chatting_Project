package com.example.chat.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class IsProDTO {
    private String roomId;  //룸번호

    private String kakaoId; //카카오 고유 번호
    private int isPro;

    @Builder
    public IsProDTO(String roomId, String kakaoId, int isPro){
        this.roomId = roomId;
        this.kakaoId = kakaoId;
        this.isPro = isPro;
    }
}
