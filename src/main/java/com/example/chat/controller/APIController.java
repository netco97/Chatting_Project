package com.example.chat.controller;

import com.example.chat.dto.KakaoDTO;
import com.example.chat.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class APIController {

    private final MemberService memberService;

    @GetMapping("/member")
    public String findAll(Model model){
        List<KakaoDTO> kakaoDTOList = memberService.findAll();
        model.addAttribute("kakaoList", kakaoDTOList);

        //Test 찍어봣음
        System.out.println(model);
        //아직 API 쓸데가 없음
        return "kakao_api";
    }
}
