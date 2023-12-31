package com.example.chat.entity;

import com.example.chat.dto.KakaoDTO;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "kakao_table")
public class User {

    @Id
    private Long k_number;

    @Column(nullable = false)
    private String k_name;

    @Column(nullable = false)
    private String k_email;

    @Column(nullable = false)
    private int isAdmin;

    public static User toUserEntity(KakaoDTO kakaoDTO)//DTO to Entity
    {
        User user = new User();
        user.k_number = kakaoDTO.getK_number();
        user.k_name = kakaoDTO.getK_name();
        user.k_email = kakaoDTO.getK_email();
        user.isAdmin = kakaoDTO.getIsAdmin();

        return user;
    }
}
