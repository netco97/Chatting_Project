package com.example.chat.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
public class InformationDTO {
    private String roomId; //룸아이디
    private int pro; //찬성
    private int con; //반대

    private String topic;

    public InformationDTO(String roomId, int pro, int con,String topic){
        this.roomId = roomId;
        this.pro = pro;
        this.con = con;
        this.topic = topic;
    }
}
