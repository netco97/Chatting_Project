package com.example.chat.controller;

import com.example.chat.Repository.VoteRepository;
import com.example.chat.dto.ChatRoom;
import com.example.chat.entity.VoteEntity;
import com.example.chat.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RequestMapping("/chat")
public class ChatRoomController {
    private final ChatService chatService;
    private final VoteRepository voteRepository;




    // 채팅 리스트 화면
    @GetMapping("/room")
    public String rooms(Model model) {
        return "/chat/room";
    }
    // 모든 채팅방 목록 반환
    @GetMapping("/rooms")
    @ResponseBody
    public List<ChatRoom> room() {
        return chatService.findAllRoom();
    }
    // 채팅방 생성
    @PostMapping(value = "/room")
    @ResponseBody
    public ChatRoom createRoom(@RequestParam String name) {

        // RoomId 를 API에서 따와서 그걸 /room 하고 /room/enter/{id}로 보내주면 만들떄 roomname도 같이 보낼수있을것같음
        return chatService.createChatRoom(name); //chat/room post통신에서 name을 쓸수있읍니다.
    }

    // 채팅방 입장 화면
    @GetMapping("/room/enter/{roomId}")
    public String roomDetail(Model model, @PathVariable String roomId) {
        String pro_rate = "0";
        String con_rate = "0";
        Optional<VoteEntity> result = voteRepository.findById(roomId);

        //Vote DB가 존재하면
        if(result.isPresent())
        {
            double pro_count = (double) voteRepository.proCount(roomId);
            double con_count = (double) voteRepository.conCount(roomId);

            double total_count = (double) (pro_count+con_count);

            pro_rate = String.valueOf(Math.round(((pro_count / total_count)*100)*100)/100.0);
            con_rate = String.valueOf(Math.round(((con_count / total_count)*100)*100)/100.0);
        }

        model.addAttribute("view_pro_rate",pro_rate);
        model.addAttribute("view_con_rate",con_rate);
        model.addAttribute("roomId", roomId);

        return "/chat/roomdetail";
    }
    // 특정 채팅방 조회
    @GetMapping("/room/{roomId}")
    @ResponseBody
    public ChatRoom roomInfo(@PathVariable String roomId) {
        return chatService.findRoomById(roomId);
    }
}