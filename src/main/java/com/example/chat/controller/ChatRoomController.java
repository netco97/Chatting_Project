package com.example.chat.controller;

import com.example.chat.Repository.InformationRepository;
import com.example.chat.dto.ChatRoom;
import com.example.chat.entity.FileEntity;
import com.example.chat.entity.InformationEntity;
import com.example.chat.service.ChatService;
import com.example.chat.service.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RequestMapping("/chat")
public class ChatRoomController {
    private final ChatService chatService;
    private final FileService fileService;
    private final InformationRepository informationRepository;


    // 채팅 리스트 화면
    @GetMapping("/room")
    public String rooms() {
        return "/chat/room";
    }
    // 모든 채팅방 목록 반환
    @GetMapping("/rooms")
    @ResponseBody
    public List<ChatRoom> room() {
        return chatService.findAllRoom();
    }


    @PostMapping(value = "/room")
    @ResponseBody
    public ChatRoom createRoom(@RequestParam("uploadFile") List<MultipartFile> files, @RequestParam("room_name") String name) throws Exception{
        String fileRealName = String.valueOf(files.get(0).getOriginalFilename());
        System.out.println("파일이름 확인 " + fileRealName);

        ChatRoom chatRoom = new ChatRoom();
        chatRoom = chatService.createChatRoom(name);
        String roomId = chatRoom.getRoomId();

        // roomId chatSerivce에서 뺴와서 파일서비스에 roomId를 넣어서 boradIdx를 바꾸기
        fileService.addBoard(FileEntity.builder().build(),files,roomId);

        return chatRoom;
    }
    // 채팅방 생성 (roomid, file값)
    /*@PostMapping(value = "/room")
    @ResponseBody
    public ChatRoom createRoom(@RequestParam("uploadFile") MultipartFile file, @RequestParam("room_name") String name){
        String fileRealName = file.getOriginalFilename();
        System.out.println(fileRealName);


        return chatService.createChatRoom(name);
    }*/


    // 채팅방 입장 화면 (찬,반 투표율, 룸Id보냄) 미리 DB에 있던 찬/반 투표율 계산해서 화면에 띄어주는역할
    @GetMapping("/room/enter/{roomId}")
    public String roomDetail(Model model, @PathVariable String roomId) {
        String pro_rate = "0";
        String con_rate = "0";
        Optional<InformationEntity> result = informationRepository.findById(roomId);

        //Vote DB가 존재하면
        if(result.isPresent())
        {
            double pro_count = (double) informationRepository.proCount(roomId);
            double con_count = (double) informationRepository.conCount(roomId);

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