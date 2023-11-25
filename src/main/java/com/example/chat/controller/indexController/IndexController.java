package com.example.chat.controller.indexController;

//getUserInfo

import com.example.chat.Repository.ChatRepository;
import com.example.chat.Repository.IsProRepository;
import com.example.chat.dto.ChatRoom;
import com.example.chat.service.ChatService;
import com.example.chat.service.InformationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class IndexController {

    private final ChatRepository chatRepository;
    private final IsProRepository isProRepository;
    private final ChatService chatService;
    private final InformationService informationService;

    @GetMapping(value = "/getUserInfo/{kakaoId}")
    public Map<String, Object>getUserInfo(@PathVariable String kakaoId){
        Map<String, Object> result = new HashMap<String,Object>();

        result.put("nor", chatRepository.countByKakaoId(kakaoId));
        result.put("nov", isProRepository.countByKakaoId(kakaoId));

        return result;
    }

    @PostMapping(value = "/createTopic")
    @ResponseBody
    public ChatRoom createRoom(@RequestParam("topic") String topic, @RequestParam("period") String period) throws Exception{

        ChatRoom chatRoom = new ChatRoom();
        chatRoom = chatService.createChatRoom(topic);
        String roomId = chatRoom.getRoomId();

        informationService.save(roomId,topic,period);

        return chatRoom;
    }
}
