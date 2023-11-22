package com.example.chat.dto;

import lombok.*;

@Getter
@Setter
@ToString

public class ShowInfoDTO {
    private String roomId; //룸아이디
    private int pro; //찬성수
    private int con; //반대수
    private String topic; //방이름
    private String cre_date; //생성날짜
    private String reply; //채팅방 메세지 수



    @Builder
    public ShowInfoDTO(String roomId, int pro, int con, String topic, String cre_date) {
        this.roomId = roomId;
        this.pro = pro;
        this.con = con;
        this.topic = topic;
        this.cre_date = cre_date;
    }
}
