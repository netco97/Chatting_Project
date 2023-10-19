package com.example.chat.controller;

import com.example.chat.dto.VoteMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;
import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@Controller
public class VoteController {

    private final SimpMessageSendingOperations messagingTemplate;

    private double pro_count = 0;
    private double con_count = 0;
    private double total_count = 0;
    private double pro_rate = 0;
    private double con_rate = 0;

    /*@MessageMapping("/message")
    public void sendToFront(VoteMessage message){
        messagingTemplate.convertAndSend("/sub/voteMessage/" + message.getRoomId(), message);

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

    }*/

    //pub/message에서 메세지 매핑
    @MessageMapping("/message")
    @SendTo("/sub/voteMessage")
    public Map<String, Object> sendToFront(VoteMessage message){
        Map<String, Object> result = new HashMap<String,Object>();
        result.put("getValue",message.getValue());
        result.put("getRoomId",message.getRoomId());


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
        System.out.println("pro : "+result.get("getPro"));
        System.out.println("con : "+result.get("getCon"));
        System.out.println("찬성수 : "+pro_count);
        System.out.println("반대수 : "+con_count);
        System.out.println("전체투표수 : "+total_count);
        pro_rate = Math.round(((pro_count / total_count)*100)*100)/100.0;
        con_rate = Math.round(((con_count / total_count)*100)*100)/100.0;
        System.out.println("찬성 비율 : " +pro_rate);
        System.out.println("반대 비율 : " +con_rate);
        result.put("getProrate",Double.toString(pro_rate));
        result.put("getConrate",Double.toString(con_rate));

        System.out.println("Value : "+result.get("getValue"));
        System.out.println("roomId : "+result.get("getRoomId"));

        return result;
    }


}
