package com.example.chat.entity;

import com.example.chat.dto.KakaoDTO;

import javax.persistence.*;

@Entity
@Table(name = "kakao_table")
public class User {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long k_number;

    @Column(nullable = false)
    private String k_name;

    @Column(nullable = false)
    private String k_email;

    public static User toUserEntity(KakaoDTO kakaoDTO)//DTO to Entity
    {
        User user = new User();
        user.k_number = kakaoDTO.getK_number();
        user.k_name = kakaoDTO.getK_name();
        user.k_email = kakaoDTO.getK_email();

        return user;
    }
}
