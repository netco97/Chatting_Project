package com.example.chat.controller.StompController;

import com.example.chat.Repository.ChatRepository;
import com.example.chat.Repository.InformationRepository;
import com.example.chat.Repository.IsProRepository;
import com.example.chat.Repository.MemberRepository;
import com.example.chat.dto.MessageDTO;
import com.example.chat.service.ChatService;
import com.example.chat.service.InformationService;
import com.example.chat.service.VoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;

import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

@RequiredArgsConstructor
@Controller
public class StompController {
    private final IsProRepository isProRepository;
    private final InformationService informationService;
    private final VoteService voteService;
    private final SimpMessageSendingOperations messagingTemplate;
    private final InformationRepository informationRepository;
    private final ChatService chatService;
    private final ChatRepository chatRepository;
    private final MemberRepository memberRepository;


    @MessageMapping("/msg")
    public void send(MessageDTO messageDTO, Principal principal){

        // type == VOTE
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

        //type == MSG
        else if(MessageDTO.MessageType.MSG.equals(messageDTO.getType()))
        {
            String isPro = "";
            System.out.println("test---------------------");
            System.out.println(messageDTO.getType());
            System.out.println(messageDTO.getKakaoId());
            System.out.println(messageDTO.getRoomId());
            System.out.println(messageDTO.getMsg());
            //유저가 투표를 했으면
            if(isProRepository.isPro_count_check(messageDTO.getKakaoId(),messageDTO.getRoomId())==1)
            {
                if(isProRepository.isPro_check(messageDTO.getKakaoId(),messageDTO.getRoomId())==1){
                    System.out.println("찬");
                    isPro="1";
                    messageDTO.setIsPro(isPro);
                }
                else if(isProRepository.isPro_check(messageDTO.getKakaoId(),messageDTO.getRoomId())==0){
                    System.out.println("반");
                    isPro="0";
                    messageDTO.setIsPro(isPro);
                }
                String currentTime = chatService.getCurrentDateTime();

                chatService.save(messageDTO.getRoomId(),messageDTO.getMsg(),messageDTO.getKakaoId(), currentTime, isPro);
                messageDTO.setDate(currentTime);
                String name = memberRepository.k_name_select(messageDTO.getKakaoId());
                messageDTO.setSendor(chatService.maskName(name));

                System.out.println(chatService.maskName(name));
                messagingTemplate.convertAndSend("/sub/topic/" + messageDTO.getRoomId(), messageDTO);
            }

            //유저가 투표를 하지 않았으면,
            else{
                //messagingTemplate.convertAndSend("/user/"+message.getKakaoId()+"/queue/"+message.getRoomId(), message);
                messageDTO.setError("투표를 하지 않으셨습니다. 투표먼저 해주세요");
                messagingTemplate.convertAndSendToUser(principal.getName(), "/queue/"+messageDTO.getRoomId(),messageDTO);
            }
        }
    }


}
