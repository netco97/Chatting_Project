package com.example.chat.entity;

import com.example.chat.dto.ChatMessage;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@Table(name = "chat_table")
public class ChatEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String roomId;

    @Column(nullable = false)
    private String msg;

    @Column(nullable = false)
    private String kakaoId;

    @Column(nullable = false)
    private String date;

    @Column(nullable = false)
    private String isPro;

    @Column(nullable = false)
    private String sender;

    public static ChatEntity toChatEntity(ChatMessage chatMessage) {
        ChatEntity chatEntity = new ChatEntity();
        chatEntity.roomId = chatMessage.getRoomId();
        chatEntity.msg = chatMessage.getMsg();
        chatEntity.kakaoId = chatMessage.getKakaoId();
        chatEntity.date = chatMessage.getDate();
        chatEntity.isPro = chatMessage.getIsPro();
        chatEntity.sender = chatMessage.getSender();

        return chatEntity;
    }
}
