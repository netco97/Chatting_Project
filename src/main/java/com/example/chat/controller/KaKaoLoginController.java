package com.example.chat.controller;

import com.example.chat.dto.KakaoDTO;
import com.example.chat.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;

@Controller
@RequiredArgsConstructor
@RequestMapping(value = "/login")
public class KaKaoLoginController {

    private final MemberService memberService;

    @GetMapping("")
    public String login() {
        return "/login";
    }

    @GetMapping("/kakao")
    public String kakaoLogin(@RequestParam(value = "code", required = false) String code) throws Exception {
        System.out.println("#########" + code);
        String access_Token = memberService.getAccessToken(code);

        HashMap<String, Object> userInfo = memberService.getUserInfo(access_Token);

        //DB 저장
        memberService.save();

        System.out.println("###access_Token#### : " + access_Token);
        System.out.println("###nickname#### : " + userInfo.get("nickname"));
        System.out.println("###email#### : " + userInfo.get("email"));
        System.out.println("###id#### : " + userInfo.get("id"));
        return "test";
    }

}
