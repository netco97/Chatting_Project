package com.example.chat.service;

import com.example.chat.Repository.IsProRepository;
import com.example.chat.Repository.InformationRepository;
import com.example.chat.dto.IsProDTO;
import com.example.chat.dto.VoteMessage;
import com.example.chat.entity.IsProEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class VoteService {
    private final IsProRepository isProRepository;


    //찬반 테이블 save
    public void isProsave(String roomId, String kakaoId, int pro) {

        IsProDTO isProDTO = IsProDTO.
                    builder()
                    .roomId(roomId)
                    .kakaoId(kakaoId)
                    .isPro(pro)
                    .build();
        IsProEntity isProEntity = IsProEntity.toIsProEntity(isProDTO);
        isProRepository.save(isProEntity);
    }

}