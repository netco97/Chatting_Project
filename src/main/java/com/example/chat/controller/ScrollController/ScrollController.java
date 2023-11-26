package com.example.chat.controller.ScrollController;

import com.example.chat.Repository.MemberRepository;
import com.example.chat.dto.ChatDTO;
import com.example.chat.dto.ChatDTO2;
import com.example.chat.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class ScrollController {
    private final ChatService chatService;
    private final MemberRepository memberRepository;

    @GetMapping("/msgScrollXhr/{roomId}/{isPro}/{count}")
    public List<ChatDTO> scroll(@PathVariable String roomId, @PathVariable String isPro, @PathVariable int count){

        List<ChatDTO2> result = chatService.proPrevLoad(roomId,isPro,count);
        List<ChatDTO> convertedResult = new ArrayList<>();
        for(ChatDTO2 chatDTO2 : result)
        {
            System.out.println("-----테스트--------");
            System.out.println(chatDTO2.getId());
            System.out.println(chatDTO2.getRoomId());
            System.out.println(chatDTO2.getDate());
            System.out.println(chatDTO2.getIsPro());
            System.out.println(chatDTO2.getKakaoId());
            System.out.println(chatDTO2.getMsg());
            System.out.println(chatDTO2.getSender());
            System.out.println("------테스트 --------");

            ChatDTO chatDTO = ChatDTO.builder()
                    .id(chatDTO2.getId())
                    .roomId(chatDTO2.getRoomId())
                    .msg(chatDTO2.getMsg())
                    .kakaoId(chatDTO2.getKakaoId())
                    .date(chatDTO2.getDate())
                    .sender(chatDTO2.getSender())
                    .isPro(chatDTO2.getIsPro())
                    .build();

            convertedResult.add(chatDTO);
        }

        return convertedResult;
    }
}
