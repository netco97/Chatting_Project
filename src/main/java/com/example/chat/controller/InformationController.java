package com.example.chat.controller;

import com.example.chat.Repository.ChatRepository;
import com.example.chat.dto.ShowInfoDTO;
import com.example.chat.service.InformationService;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import org.json.JSONArray;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Controller
@RequiredArgsConstructor

public class InformationController {
    private final InformationService informationService;
    private final ChatRepository chatRepository;

    Random rd = new Random();

    @GetMapping("/api/information")
    @ResponseBody
    public List<ShowInfoDTO> show_information(){

        List<ShowInfoDTO> list = informationService.showInfoDTOS();
        for(ShowInfoDTO showInfoDTO : list) {
            showInfoDTO.setReply(String.valueOf(chatRepository.countByRoomId(showInfoDTO.getRoomId())));
            showInfoDTO.setPeriod(String.valueOf(rd.nextInt(15)+7));
            showInfoDTO.setOpened(true);
            System.out.println(showInfoDTO);
        }

        return list;

        //일요일안에 합쳐/ 월요일부터 배포
    }
}
