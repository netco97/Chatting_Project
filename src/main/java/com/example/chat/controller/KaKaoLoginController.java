package com.example.chat.controller;

import com.example.chat.service.GetUserInfoService;
import com.example.chat.service.RestJsonService;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/oauth")
public class KaKaoLoginController {

    @GetMapping("/kakao")
    public String receiveAC(@RequestParam("code") String code, Model model){
        System.out.println(code);
        RestJsonService restJsonService = new RestJsonService();

        //access_token이 포함된 JSON String을 받아온다.
        String accessTokenJsonData = restJsonService.getAccessTokenJsonData(code);
        if(accessTokenJsonData=="error") return "/chat/error";


        //JSON String -> JSON Object
        JSONObject accessTokenJsonObject = new JSONObject(accessTokenJsonData);

        //access_token 추출
        String accessToken = accessTokenJsonObject.get("access_token").toString();

        //유저 정보가 포함된 JSON String을 받아온다.
        GetUserInfoService getUserInfoService = new GetUserInfoService();
        String userInfo = getUserInfoService.getUserInfo(accessToken);

        //JSON String -> JSON Object
        JSONObject userInfoJsonObject = new JSONObject(userInfo);

        //유저의 Email 추출
        JSONObject kakaoAccountJsonObject = (JSONObject)userInfoJsonObject.get("kakao_account");
        String email = kakaoAccountJsonObject.get("email").toString();



        //View에서 사용할 변수 설정
        model.addAttribute("email", email);

        System.out.println(email);

        return "/chat/room";
    }

}
