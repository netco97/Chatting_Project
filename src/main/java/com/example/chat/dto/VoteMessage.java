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
    private String userId; // 유저아이디
    private String topic; // 방이름
    private String kakaoId; //카카오 고유 ID
    private String error; //중복처리 에러메세지


}