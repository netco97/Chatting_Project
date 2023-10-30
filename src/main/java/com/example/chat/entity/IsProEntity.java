package com.example.chat.entity;

import com.example.chat.dto.ChatMessage;
import com.example.chat.dto.IsProDTO;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "isPro_table")
public class IsProEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String roomId;

    @Column
    private String userId;

    @Column
    private int isPro;

    public static IsProEntity toIsProEntity(IsProDTO isProDTO) {
        IsProEntity isProEntity = new IsProEntity();
        isProEntity.roomId = isProDTO.getRoomId();
        isProEntity.userId = isProDTO.getUserId();
        isProEntity.isPro = isProDTO.getIsPro();

        return isProEntity;
    }
}
