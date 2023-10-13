package com.example.chat.dto;

import com.example.chat.entity.User;
import lombok.*;

@Getter
@Setter
public class KakaoDTO {

    private long k_number;
    private String k_name;
    private String k_email;

    public KakaoDTO(){

    }
    @Builder
    public KakaoDTO (long k_number, String k_name, String k_email){
        this.k_number = k_number;
        this.k_name = k_name;
        this.k_email = k_email;
    }

    public static KakaoDTO toKakaoDTO(User user){
        KakaoDTO kakaoDTO = new KakaoDTO();
        kakaoDTO.setK_number(user.getK_number());
        kakaoDTO.setK_name(user.getK_name());
        kakaoDTO.setK_email(user.getK_email());

        return kakaoDTO;
    }
}
