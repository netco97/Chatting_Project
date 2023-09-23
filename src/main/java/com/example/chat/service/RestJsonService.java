package com.example.chat.service;

public class RestJsonService {
    private final String GRANT_TYPE= "authorization_code";
    private final String CLIENT_ID = "41edb156a54ce9db651ea3f661053d5d";
    private final String REDIRECT_URI= "http://localhost:8080/oauth/kakao";
    private final String CLIENT_SECRET= "8bXuR"; //이하 생략
    private final String TOKEN_URL = "https://kauth.kakao.com/oauth/token";
}
