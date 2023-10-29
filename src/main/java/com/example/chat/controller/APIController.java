package com.example.chat.controller;

import com.example.chat.Repository.VoteRepository;
import com.example.chat.dto.KakaoDTO;
import com.example.chat.service.MemberService;
import com.example.chat.service.VoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class APIController {
    private final VoteRepository voteRepository;
    private final MemberService memberService;

    //kakao회원정보
    @GetMapping("/member")
    public String findAll(Model model){
        List<KakaoDTO> kakaoDTOList = memberService.findAll();
        model.addAttribute("kakaoList", kakaoDTOList);
        //Test 찍어봣음
        System.out.println(model);
        //아직 API 쓸데가 없음 (이거 나중에 Model and view 로 만들면 여러개 뷰에다가 모델 쏴줄수 있을것같음 생각날때 미리 적어둠)

        return "kakao_api";
    }

}
