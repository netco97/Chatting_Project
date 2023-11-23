package com.example.chat.dto;

import lombok.*;

@Getter
@Setter
@ToString

public class ShowInfoDTO {
    private String roomId; //룸아이디
    private int pros; //찬성수
    private int cons; //반대수
    private String topic; //방이름
    private String date; //생성날짜
    private String reply; //채팅방 메세지 수
    private String period; //기간
    private boolean isOpened; //방open유무


    @Builder
    public ShowInfoDTO(String roomId, int pros, int cons, String topic, String cre_date) {
        this.roomId = roomId;
        this.pros = pros;
        this.cons = cons;
        this.topic = topic;
        this.date = cre_date;
    }
}
