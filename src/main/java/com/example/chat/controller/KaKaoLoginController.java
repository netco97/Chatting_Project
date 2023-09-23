package com.example.chat.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class KaKaoLoginController {

    @GetMapping("/oauth/kakao")
    public String receiveAC(@RequestParam("code") String code, Model model){

    }
}
