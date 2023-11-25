package com.example.chat.controller.CardController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CardController {
    //topicRoom?topic='+topic;
    @GetMapping(value = "/topicRoom")
    public String topicRoom(@RequestParam(name = "topic") String topic){
        System.out.println("topic 이름 " + topic);
        return "topicRoom.html";
    }
}
