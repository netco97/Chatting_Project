package com.example.chat.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class IsProDTO {
    private String roomId;  //룸번호
    private String userId;
    private int isPro;

    @Builder
    public IsProDTO(String roomId, String userId, int isPro){
        this.roomId = roomId;
        this.userId = userId;
        this.isPro = isPro;
    }
}
