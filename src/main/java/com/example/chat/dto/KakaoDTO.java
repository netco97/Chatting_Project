package com.example.chat.dto;

import com.example.chat.entity.User;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class KakaoDTO {

    private long k_number;
    private String k_name;
    private String k_email;

    public static KakaoDTO toKakaoDTO(User user){
        KakaoDTO kakaoDTO = new KakaoDTO();
        kakaoDTO.setK_number(user.getK_number());
        kakaoDTO.setK_name(user.getK_name());
        kakaoDTO.setK_email(user.getK_email());

        return kakaoDTO;
    }
}
