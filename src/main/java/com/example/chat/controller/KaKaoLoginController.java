package com.example.chat.controller;

import com.example.chat.dto.KakaoDTO;
import com.example.chat.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.HashMap;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
@RequiredArgsConstructor
public class KaKaoLoginController {

    private final MemberService memberService;

    @GetMapping("/login")
    public String login() {
        return "/login";
    }

    @GetMapping("/login/kakao")
    public String kakaoLogin(@RequestParam(value = "code", required = false) String code, HttpSession session) throws Exception {
        System.out.println("#########" + code);
        String access_Token = memberService.getAccessToken(code);

        HashMap<String, Object> userInfo = memberService.getUserInfo(access_Token);

        //로그인 세션 쿠키 만들어짐 -> 30분유지되도록 -> 세션을 이용해서 -> 로그인한 사람의 정보를 가져올 수 있음.

        //DB 저장
        memberService.save();

        System.out.println("###access_Token#### : " + access_Token);
        System.out.println("###nickname#### : " + userInfo.get("nickname"));
        System.out.println("###email#### : " + userInfo.get("email"));
        System.out.println("###id#### : " + userInfo.get("id"));

        //세션 로직
        if (userInfo.get("email")!=null){
            session.setMaxInactiveInterval(1800); // 1800 = 60 * 30 => 30분
            session.setAttribute("userId",userInfo.get("nickname"));
            session.setAttribute("accessToken", access_Token);
        }

        return "redirect:/chat/room";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        System.out.println(session.getAttribute("accessToken"));

        memberService.logout((String)session.getAttribute("accessToken"));
        session.removeAttribute("accessToken");
        session.removeAttribute("userId");

        return "redirect:/chat/room";
    }

}
