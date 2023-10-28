package com.example.chat.controller;

import com.example.chat.dto.ChatMessage;
import com.example.chat.dto.ChatRoom;
import com.example.chat.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;

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
            message.setMessage(message.getSender() + "님이 입장하셨습니다.");




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
}
