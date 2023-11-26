package com.example.chat.controller.ChatLoadController;

import com.example.chat.Repository.MemberRepository;
import com.example.chat.dto.ChatDTO;
import com.example.chat.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequiredArgsConstructor
public class ChatLoadController {
    private final ChatService chatService;
    private final MemberRepository memberRepository;

    @GetMapping("/msgInitXhr/{roomId}")
    public void chatLoad(@PathVariable String roomId){

        List<ChatDTO> pro_list = chatService.proChatLoad(roomId);
        List<ChatDTO> con_list = chatService.conChatLoad(roomId);
        System.out.println("--------찬성 dto---------");
        for(ChatDTO chatDTO : pro_list)
        {
            System.out.println(chatDTO);
        }
        System.out.println("------반대 dto--------");
        for(ChatDTO chatDTO : con_list)
        {
            System.out.println(chatDTO);
        }

        System.out.println("-------합 dto--------");
        List<ChatDTO> joined = Stream.concat(pro_list.stream(), con_list.stream()).collect(Collectors.toList());

        for(ChatDTO chatDTO : joined)
        {
            String name = memberRepository.k_name_select(chatDTO.getKakaoId());
            chatDTO.setSender(chatService.maskName(name));
            System.out.println(chatDTO);
        }
    }
}
