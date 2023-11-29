package com.example.chat.controller.CardController;

import com.example.chat.Repository.InformationRepository;
import com.example.chat.dto.InformationDTO;
import com.example.chat.entity.InformationEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@RequiredArgsConstructor
@Controller
public class CardController {
    private final InformationRepository informationRepository;
    //topicRoom?topic='+topic;
    @GetMapping(value = "/topicRoom")
    public String topicRoom(@RequestParam(name = "roomId") String roomId, Model model){
        System.out.println("topic roomId " + roomId);

        String pro_rate = "0";
        String con_rate = "0";
        Optional<InformationEntity> result = informationRepository.findById(roomId);
        String topic = "";

        //Vote DB가 존재하면
        if(result.isPresent())
        {
            double pro_count = (double) informationRepository.proCount(roomId);
            double con_count = (double) informationRepository.conCount(roomId);

            double total_count = (double) (pro_count+con_count);

            pro_rate = String.valueOf(Math.round(((pro_count / total_count)*100)*100)/100.0);
            con_rate = String.valueOf(Math.round(((con_count / total_count)*100)*100)/100.0);

            topic = informationRepository.Select_topic(roomId);
        }

        model.addAttribute("topic", topic);
        model.addAttribute("view_pro_rate",pro_rate);
        model.addAttribute("view_con_rate",con_rate);
        model.addAttribute("roomId", roomId);
        model.addAttribute("date",result.get().getCreatedDate());
        model.addAttribute("period",result.get().getPeriod());

        return "topicRoom.html";
    }
}
