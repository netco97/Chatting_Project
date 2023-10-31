package com.example.chat.service;

import com.example.chat.Repository.InformationRepository;
import com.example.chat.dto.InformationDTO;
import com.example.chat.dto.VoteMessage;
import com.example.chat.entity.InformationEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class InformationService {
        private final InformationRepository informationRepository;

    public void update(String roomId,String pro,String con,String topic) {
        InformationDTO informationDTO = new InformationDTO(roomId,Integer.parseInt(pro),Integer.parseInt(con),topic);
        InformationEntity informationEntity = InformationEntity.toInformationEntity(informationDTO);


        //룸아이디 중복체크(이거안하면 기본 repository save불러와져서 update all set되어버림
        Optional<InformationEntity> optionalInformationEntity = informationRepository.findById(roomId);
        if(optionalInformationEntity.isPresent()) //update set
        {
            System.out.println("pro " +pro +"con "+ con);
            informationRepository.updateVote(Integer.parseInt(pro),Integer.parseInt(con),roomId);
        }
        else{
            informationRepository.save(informationEntity); // insert into
        }

    }

    //투표 계산 함수
    public Map<String, Object> select_vote(String roomId){

        Map<String, Object> result = new HashMap<String,Object>();

        int pro_count = informationRepository.proCount(roomId);
        int con_count = informationRepository.conCount(roomId);

        double total_count = (double) (pro_count+con_count);

        System.out.println("전체투표수 : "+total_count);

        double pro_rate = Math.round(((pro_count / total_count)*100)*100)/100.0;
        double con_rate = Math.round(((con_count / total_count)*100)*100)/100.0;

        System.out.println("찬성 비율 : " +pro_rate);
        System.out.println("반대 비율 : " +con_rate);

        result.put("getProrate",pro_rate);
        result.put("getConrate",con_rate);

        return result;
    }
}
