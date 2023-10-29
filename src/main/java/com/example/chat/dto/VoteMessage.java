package com.example.chat.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VoteMessage {
    private String roomId;  //룸번호
    private String pro; //찬성
    private String con; //반대
    private String prorate; //찬성비율
    private String conrate; //반대비율

    @Builder
    public VoteMessage(String roomId, String pro, String con){
        this.roomId = roomId;
        this.pro = pro;
        this.con = con;
    }
}