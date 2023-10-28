package com.example.chat.entity;

import com.example.chat.dto.ChatMessage;
import com.example.chat.dto.VoteMessage;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@Table(name = "vote_table")
public class VoteEntity {

    @Id
    private String roomId;

    @Column
    private int Pro;

    @Column
    private int Con;


    public static VoteEntity toVoteEntity(VoteMessage voteMessage) {
        VoteEntity voteEntity = new VoteEntity();
        voteEntity.roomId =voteMessage.getRoomId();
        voteEntity.Pro = Integer.parseInt(voteMessage.getPro());
        voteEntity.Con = Integer.parseInt(voteMessage.getCon());
        return voteEntity;
    }
}
