package com.example.chat.dto;

import com.example.chat.entity.InformationEntity;
import com.example.chat.entity.User;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
public class InformationDTO {
    private String roomId; //룸아이디
    private int pro; //찬성
    private int con; //반대

    private String topic;

    @Builder
    public InformationDTO(String roomId, int pro, int con,String topic){
        this.roomId = roomId;
        this.pro = pro;
        this.con = con;
        this.topic = topic;
    }

    public static InformationDTO toInformationDTO(InformationEntity informationEntity){
        InformationDTO informationDTO = new InformationDTO();
        informationDTO.setRoomId(informationEntity.getRoomId());
        informationDTO.setPro(informationEntity.getPro());
        informationDTO.setCon(informationEntity.getCon());
        informationDTO.setTopic(informationEntity.getTopic());

        return informationDTO;
    }
}
