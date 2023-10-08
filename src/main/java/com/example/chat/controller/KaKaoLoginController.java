package com.example.chat.controller;

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

    private final MemberService ms;
    @GetMapping("")
    public String login(){
        return "/login";
    }

    @GetMapping("/kakao")
    public String kakaoLogin(@RequestParam(value = "code", required = false)String code){
        System.out.println("###########"+code);

        String access_Token = ms.getAccessToken(code);

        HashMap<String, Object> userInfo = ms.getUserInfo(access_Token);
        System.out.println("###access_Token### : " + access_Token);
        System.out.println("###nickname#### : " + userInfo.get("nickname"));
        System.out.println("###email#### : " + userInfo.get("email"));

        return "/test";

    }
}
