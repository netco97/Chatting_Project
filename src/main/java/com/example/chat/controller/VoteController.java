/*package com.example.chat.controller;

import com.example.chat.Repository.IsProRepository;
import com.example.chat.dto.VoteMessage;
import com.example.chat.service.InformationService;
import com.example.chat.service.VoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@Controller
public class VoteController {
    private final SimpMessageSendingOperations messagingTemplate;
    private final VoteService voteService;
    private final InformationService informationService;
    private final IsProRepository isProRepository;



    @MessageMapping("/message")
    public void sendToFront(VoteMessage message){

        System.out.println("ㅡㅡㅡㅡㅡㅡㅡㅡVote DB testㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ");
        System.out.println(message.getRoomId());
        System.out.println(message.getPro());
        System.out.println(message.getCon());
        System.out.println(message.getUserId());
        System.out.println(message.getTopic());
        System.out.println(message.getKakaoId());

        // kakaoId, RoomId로 중복체크
        if(isProRepository.duplicate_check(message.getKakaoId(),message.getRoomId())>=1){
            message.setProrate(message.getProrate());
            message.setConrate(message.getConrate());
            message.setError("이미 투표 하셨습니다.");
            Map<String, Object> result = informationService.select_vote(message.getRoomId());
            message.setProrate(String.valueOf(result.get("getProrate")));
            message.setConrate(String.valueOf(result.get("getConrate")));
        }

        else{
            //isPro_table create
            voteService.isProsave(message.getRoomId(),message.getKakaoId(),Integer.parseInt(message.getPro()));
            //insert into or update
            informationService.update(message.getRoomId(),message.getPro(),message.getCon(),message.getTopic());

            Map<String, Object> result = informationService.select_vote(message.getRoomId());
            System.out.println(result);

            message.setProrate(String.valueOf(result.get("getProrate")));
            message.setConrate(String.valueOf(result.get("getConrate")));
        }

        messagingTemplate.convertAndSend("/sub/voteMessage/" + message.getRoomId(), message);
    }






 // 여기서부터는 원래 안씀
    //pub/message에서 메세지 매핑
    /*@MessageMapping("/message/{roomId}")
    @SendTo("/sub/voteMessage/{roomId}")
    public Map<String, Object> sendToFront(@DestinationVariable String roomId, VoteMessage message){
        Map<String, Object> result = new HashMap<String,Object>();

        System.out.println("파라미터 룸id test "+roomId);
        System.out.println("test " + message.getPro());

        //if(result.get("getPro")!=null){
        if(message.getPro()!=null){
            System.out.println("찬성");
            result.put("getPro",message.getPro());
        }
        else if(message.getCon()!=null){
            System.out.println("반대");
            result.put("getCon",message.getCon());
        }

        System.out.println(result.get("getPro"));
        System.out.println(result.get("getCon"));
        return result;
    }*/

//}

/* 계산하는 식 생각해봄 나중에 DB에서 불러옴
* System.out.println("찬성수 : "+pro_count);
        System.out.println("반대수 : "+con_count);
        System.out.println("전체투표수 : "+total_count);
        pro_rate = Math.round(((pro_count / total_count)*100)*100)/100.0;
        con_rate = Math.round(((con_count / total_count)*100)*100)/100.0;
        System.out.println("찬성 비율 : " +pro_rate);
        System.out.println("반대 비율 : " +con_rate);

        message.setPro(String.valueOf(pro_rate));
        message.setCon(String.valueOf(con_rate));
        * */
