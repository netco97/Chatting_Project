package com.example.chat.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class MessageDTO {
    public enum MessageType {
        VOTE, MSG
    }

    // -------------------------------- VOTE ------------------------------
    //get
    private MessageType type;
    private String roomId; // roomId
    private String kakaoId; // kakaoId
    private String isPro; // isPro

    //set
    private int proTotal;
    private int conTotal;
    private String error;
    private String isSuccess;


    // ----------------------------------- CHAT -------------------------------
    private String msg;
    private String sender;
    private String date;
}
