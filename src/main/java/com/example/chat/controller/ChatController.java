package com.example.chat.controller;

import com.example.chat.dto.ChatDTO;
import com.example.chat.dto.ChatMessage;
import com.example.chat.dto.ChatRoom;
import com.example.chat.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Controller
public class ChatController {

    private final SimpMessageSendingOperations messagingTemplate;
    private final ChatService chatService;

    /**
     * websocket "/pub/chat/message"로 들어오는 메시징을 처리한다.
     */
    @MessageMapping("/chat/message")
    public void message(ChatMessage message) {
        if (ChatMessage.MessageType.ENTER.equals(message.getType()))
        {
            message.setMessage(message.getSender() + "님이 입장하셨습니다.");
        }

        messagingTemplate.convertAndSend("/sub/chat/room/" + message.getRoomId(), message);

        String roomId = message.getRoomId();
        String sender = message.getSender();
        String message_content = message.getMessage();
        System.out.println("--------------------체크용------------------");
        System.out.println("RoomId : " + roomId);
        System.out.println("Sender : " + sender);
        System.out.println("Message : "+ message_content);
        System.out.println("message type : "+ message.getType());
        System.out.println("--------------------체크용------------------");

        //Message to DB
        if(ChatMessage.MessageType.TALK.equals(message.getType()))
            chatService.save(roomId,sender,message_content);
    }

    @GetMapping("/chat/messageList/{roomId}")
    @ResponseBody
    public List<ChatDTO> listChat(@PathVariable String roomId){
        System.out.println("roomId : "+ roomId);
        List<ChatDTO> list = chatService.listChat(roomId);
        for (ChatDTO chatDTO : list) {
            System.out.println(chatDTO);
        }
        System.out.println();

        //orderby id desc 으로 불러온 내용을 front에서 제일최근(id가 가장높은 순으로 메세지를 출력하면 반대가 되므로 한번 reverse해주었음
        Collections.reverse(list);
        return list;
    }

}
