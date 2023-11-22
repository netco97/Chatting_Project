package com.example.chat.controller;

import com.example.chat.Repository.ChatRepository;
import com.example.chat.dto.ShowInfoDTO;
import com.example.chat.service.InformationService;
import lombok.RequiredArgsConstructor;
import org.json.JSONArray;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequiredArgsConstructor

public class InformationController {
    private final InformationService informationService;
    private final ChatRepository chatRepository;

    @GetMapping("api/information")
    @ResponseBody
    public String show_information(){

        List<ShowInfoDTO> list = informationService.showInfoDTOS();
        for(ShowInfoDTO showInfoDTO : list){
            showInfoDTO.setReply(String.valueOf(chatRepository.countByRoomId(showInfoDTO.getRoomId())));
            System.out.println(showInfoDTO);
        }

        JSONArray jsonArray = new JSONArray();
        jsonArray.put(list);
        System.out.println(jsonArray);

        return jsonArray.toString();
    }
}
