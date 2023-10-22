package com.example.chat.controller;

import com.example.chat.dto.VoteMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;
import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@Controller
public class VoteController {
    private double pro_count = 0;
    private double total_count = 0;
    private double con_count  = 0;
    private double pro_rate = 0;
    private double con_rate = 0;
    private final SimpMessageSendingOperations messagingTemplate;



    @MessageMapping("/message")
    public void sendToFront(VoteMessage message){

        //if(result.get("getPro")!=null){
        if(message.getPro()!=null){
            System.out.println("찬성");
            pro_count++;
            total_count++;
        }
        else if(message.getCon()!=null){
            System.out.println("반대");
            con_count++;
            total_count++;
        }

        System.out.println("찬성수 : "+pro_count);
        System.out.println("반대수 : "+con_count);
        System.out.println("전체투표수 : "+total_count);
        pro_rate = Math.round(((pro_count / total_count)*100)*100)/100.0;
        con_rate = Math.round(((con_count / total_count)*100)*100)/100.0;
        System.out.println("찬성 비율 : " +pro_rate);
        System.out.println("반대 비율 : " +con_rate);

        message.setPro(String.valueOf(pro_rate));
        message.setCon(String.valueOf(con_rate));

        messagingTemplate.convertAndSend("/sub/voteMessage/" + message.getRoomId(), message);

    }


    /*//pub/message에서 메세지 매핑
    @MessageMapping("/message/{roomId}")
    @SendTo("/sub/voteMessage/{roomId}")
    public Map<String, Object> sendToFront(@DestinationVariable String roomId, VoteMessage message){
        Map<String, Object> result = new HashMap<String,Object>();

        System.out.println("파라미터 룸id test "+roomId);

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

}
