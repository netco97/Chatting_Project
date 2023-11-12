package com.example.chat.controller;

import com.example.chat.Repository.IsProRepository;
import com.example.chat.dto.ChatDTO;
import com.example.chat.dto.ChatMessage;
import com.example.chat.dto.ChatRoom;
import com.example.chat.service.ChatService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.Response;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.SimpMessageType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Controller
public class ChatController {

    private final SimpMessageSendingOperations messagingTemplate;
    private final ChatService chatService;
    private final IsProRepository isProRepository;

    /**
     * websocket "/pub/chat/message"로 들어오는 메시징을 처리한다.
     */
    @MessageMapping("/chat/message")
    public void message(ChatMessage message, Principal principal) {
        if (ChatMessage.MessageType.ENTER.equals(message.getType()))
        {
            message.setMessage(message.getSender() + "님이 입장하셨습니다.");
            //message.setKakaoId(message.getKakaoId()); 막 입장해서 받을수가 없네. 딱히필요없으니 배제
        }

        String roomId = message.getRoomId();
        String sender = message.getSender();
        String message_content = message.getMessage();
        System.out.println("--------------------체크용------------------");
        System.out.println("RoomId : " + roomId);
        System.out.println("Sender : " + sender);
        System.out.println("Message : "+ message_content);
        System.out.println("message type : "+ message.getType());
        System.out.println("kakaoID : "+ message.getKakaoId());
        System.out.println("--------------------체크용------------------");


        if(ChatMessage.MessageType.TALK.equals(message.getType()))
        {

            //유저가 투표를 했으면
            if(isProRepository.isPro_count_check(message.getKakaoId(),message.getRoomId())==1)
            {
                if(isProRepository.isPro_check(message.getKakaoId(),message.getRoomId())==1){
                    message.setIsProType("찬성");
                    System.out.println("찬");
                }
                else if(isProRepository.isPro_check(message.getKakaoId(),message.getRoomId())==0){
                    message.setIsProType("반대");
                    System.out.println("반");
                }
                chatService.save(roomId,sender,message_content,message.getKakaoId());
                messagingTemplate.convertAndSend("/sub/chat/room/" + message.getRoomId(), message);
            }

            //유저가 투표를 하지 않았으면,
            else{
                //messagingTemplate.convertAndSend("/user/"+message.getKakaoId()+"/queue/"+message.getRoomId(), message);
                message.setIsProType("투표X");
                message.setMessage("투표를 하지 않으셨습니다. 투표먼저 해주세요");
                messagingTemplate.convertAndSendToUser(principal.getName(), "/queue/"+message.getRoomId(),message);
            }


    }
}


    // 최근 메세지 10개 로드
    @GetMapping("/chat/messageList/{roomId}/{kakaoId}")
    @ResponseBody
    public List<ChatDTO> listChat(@PathVariable String roomId, @PathVariable String kakaoId){
        System.out.println("roomId : "+ roomId);
        List<ChatDTO> list = chatService.listChat(roomId);

        //Chat DTO에서 @builder 안해놓으면 값 안불러와짐 !!
        for (ChatDTO chatDTO : list) {
            if(isProRepository.isPro_count_check(chatDTO.getKakaoId(),roomId)==1)
            {
                // 찬성이고, 본인 아이디면 본인 메세지는 찬성으로
                if(isProRepository.isPro_check(chatDTO.getKakaoId(),roomId)==1){
                    chatDTO.setIsProType("찬성");
                }
                // 반대이고, 본인 아이디면 본인 메세지는 반대로
                else if(isProRepository.isPro_check(chatDTO.getKakaoId(),roomId)==0) {
                    chatDTO.setIsProType("반대");
                }
            }
            else{
                chatDTO.setIsProType("투표X");
            }
            System.out.println(chatDTO);
        }

        //orderby id desc 으로 불러온 내용을 front에서 제일최근(id가 가장높은 순으로 메세지를 출력하면 반대가 되므로 한번 reverse해주었음
        // 프론트에서 어떻게 출력하냐에 따라서 반대로 설정하면될듯
        Collections.reverse(list);
        return list;
    }

    @GetMapping("/chat/messageList/prev/{roomId}/{kakaoId}")
    @ResponseBody
    public List<ChatDTO> prevChat(@PathVariable String roomId, @PathVariable String kakaoId, @RequestParam String id) {
        List<ChatDTO> list = chatService.prevChat(roomId,Long.parseLong(id));
        System.out.println("db_id출력 " + id);
        for(ChatDTO chatDTO : list){
            if(isProRepository.isPro_count_check(chatDTO.getKakaoId(),roomId)==1)
            {
                // 찬성이고, 본인 아이디면 본인 메세지는 찬성으로
                if(isProRepository.isPro_check(chatDTO.getKakaoId(),roomId)==1){
                    chatDTO.setIsProType("찬성");
                }
                // 반대이고, 본인 아이디면 본인 메세지는 반대로
                else if(isProRepository.isPro_check(chatDTO.getKakaoId(),roomId)==0) {
                    chatDTO.setIsProType("반대");
                }
            }
            else{
                chatDTO.setIsProType("투표X");
            }
            System.out.println(chatDTO);
        }


        //여기서는 front에서 append가 아닌 , prepend이므로 reverse를 하면 안됨.

        return list;
    }
}
