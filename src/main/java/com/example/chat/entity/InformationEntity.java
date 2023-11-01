package com.example.chat.entity;

import com.example.chat.dto.InformationDTO;
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
@Table(name = "information_table")
public class InformationEntity extends BaseTimeEntity{

    @Id
    private String roomId;

    @Column
    private int Pro;

    @Column
    private int Con;

    @Column
    private String Topic;


    public static InformationEntity toInformationEntity(InformationDTO informationDTO) {
        InformationEntity informationEntity = new InformationEntity();
        informationEntity.roomId =informationDTO.getRoomId();
        informationEntity.Pro = informationDTO.getPro();
        informationEntity.Con = informationDTO.getCon();
        informationEntity.Topic = informationDTO.getTopic();

        return informationEntity;
    }
}
