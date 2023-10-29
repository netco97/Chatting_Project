package com.example.chat.service;

import com.example.chat.Repository.VoteRepository;
import com.example.chat.dto.KakaoDTO;
import com.example.chat.dto.VoteMessage;
import com.example.chat.entity.User;
import com.example.chat.entity.VoteEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class VoteService {
    private final VoteRepository voteRepository;



    public void update(String roomId,String pro,String con) {
        VoteMessage voteMessage = new VoteMessage(roomId,pro,con);
        VoteEntity voteEntity = VoteEntity.toVoteEntity(voteMessage);


        //룸아이디 중복체크(이거안하면 기본 repository save불러와져서 update all set되어버림
        Optional<VoteEntity> optionalVoteEntity = voteRepository.findById(roomId);
        if(optionalVoteEntity.isPresent()) //update set
        {
            System.out.println("pro " +pro +"con "+ con);
           voteRepository.updateVote(Integer.parseInt(pro),Integer.parseInt(con),roomId);
        }
        else{
            voteRepository.save(voteEntity); // insert into
        }

    }

    //투표 계산 함수
    public Map<String, Object> select_vote(String roomId){

        Map<String, Object> result = new HashMap<String,Object>();

        int pro_count = voteRepository.proCount(roomId);
        int con_count = voteRepository.conCount(roomId);

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

    // vote_table DB를  미리 select해준 api만들어서 API컨트롤러에서 roomdetail에다가
    // roomId에 따라서 프론트에 model로 보내서 그걸로 가져오도록 하면되지않을까싶음

}