package com.example.chat.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VoteMessage {
    private String roomId;  //룸번호
    private String pro; //찬성
    private String con; //반대
}