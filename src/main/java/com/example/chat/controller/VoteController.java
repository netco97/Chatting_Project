package com.example.chat.controller;

import com.example.chat.dto.HelloMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@Controller
public class VoteController {
    @GetMapping("/vote")
    public String vote(){

        return "/vote";
    }

    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public String value_re(HelloMessage message){
        System.out.println(message);
        return message.getValue();
    }


}
