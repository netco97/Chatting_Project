package com.example.chat.service;

import com.example.chat.Repository.VoteRepository;
import com.example.chat.dto.VoteMessage;
import com.example.chat.entity.VoteEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

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
}