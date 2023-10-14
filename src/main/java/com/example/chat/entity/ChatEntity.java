package com.example.chat.entity;

import com.example.chat.dto.ChatMessage;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

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
    private String sender;

    @Column(nullable = false)
    private String message;

    public static ChatEntity toChatEntity(ChatMessage chatMessage) {
        ChatEntity chatEntity = new ChatEntity();
        chatEntity.roomId = chatMessage.getRoomId();
        chatEntity.sender = chatMessage.getSender();
        chatEntity.message = chatMessage.getMessage();

        return chatEntity;
    }
}
