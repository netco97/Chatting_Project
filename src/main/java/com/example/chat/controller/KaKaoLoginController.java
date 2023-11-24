package com.example.chat.controller;

import com.example.chat.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class KaKaoLoginController {
    private final MemberService memberService;

    /*@GetMapping("/login")
    public String login() {
        return "/login";
    }*/

    @GetMapping("/login/kakao")
    public Map<String, Object> kakaoLogin(@RequestParam(value = "code", required = false) String code, HttpSession session) throws Exception {
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
            session.setAttribute("kakaoId",userInfo.get("id"));
        }

        Map<String, Object> sessionStore = new HashMap<>();
        sessionStore.put("userId",session.getAttribute("userId"));
        sessionStore.put("kakaoId",session.getAttribute("kakaoId"));

        System.out.println(sessionStore.get("userId"));
        System.out.println(sessionStore.get("kakaoId"));

        return sessionStore;
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        System.out.println(session.getAttribute("accessToken"));

        memberService.logout((String)session.getAttribute("accessToken"));
        session.removeAttribute("accessToken");
        session.removeAttribute("userId");
        session.removeAttribute("kakaoId");

        return "redirect:/chat/room";
    }

}
