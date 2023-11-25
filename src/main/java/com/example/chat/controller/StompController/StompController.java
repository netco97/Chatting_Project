package com.example.chat.controller.StompController;

import com.example.chat.Repository.InformationRepository;
import com.example.chat.Repository.IsProRepository;
import com.example.chat.dto.MessageDTO;
import com.example.chat.service.InformationService;
import com.example.chat.service.VoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;

import java.util.Map;

@RequiredArgsConstructor
@Controller
public class StompController {
    private final IsProRepository isProRepository;
    private final InformationService informationService;
    private final VoteService voteService;
    private final SimpMessageSendingOperations messagingTemplate;
    private final InformationRepository informationRepository;


    @MessageMapping("/msg")
    public void send(MessageDTO messageDTO){

        // type = vote
        if(MessageDTO.MessageType.VOTE.equals(messageDTO.getType()))
        {
            // kakaoId, RoomId로 중복체크
            if(isProRepository.duplicate_check(messageDTO.getKakaoId(),messageDTO.getRoomId())>=1){
                messageDTO.setError("이미 투표 하셨습니다.");
                messageDTO.setIsSuccess("fail");
            }
            else{
                //isPro_table create
                voteService.isProsave(messageDTO.getRoomId(),messageDTO.getKakaoId(),Integer.parseInt((messageDTO.getIsPro())));

                //insert into or update
                informationService.update(messageDTO.getRoomId(), messageDTO.getIsPro(), String.valueOf(1-Integer.parseInt(messageDTO.getIsPro())));

                messageDTO.setIsSuccess("success");
            }
            System.out.println("roomId 확인"+ messageDTO.getRoomId());
            System.out.println("type확인"+ messageDTO.getType());
            System.out.println("kakao" + messageDTO.getKakaoId());

            messageDTO.setProTotal(informationRepository.proCount(messageDTO.getRoomId()));
            messageDTO.setConTotal(informationRepository.conCount(messageDTO.getRoomId()));
            messagingTemplate.convertAndSend("/sub/topic/" + messageDTO.getRoomId(), messageDTO);
        }
    }
}
