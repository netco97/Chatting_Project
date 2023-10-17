package com.example.chat.controller;

import com.example.chat.dto.VoteMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.swing.text.html.HTMLDocument;
import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@Controller
public class VoteController {
    @GetMapping("/vote")
    public String vote(){

        return "/vote";
    }

    @MessageMapping("/message")
    @SendTo("/sub/voteMessage")
    public Map<String, Object> sendToFront(VoteMessage message){
        Map<String, Object> result = new HashMap<String,Object>();
        result.put("getValue",message.getValue());
        System.out.println(result.get("getValue"));
        return result;
    }


}
