package com.example.chat.dto;

import lombok.Data;

@Data
public class KakaoDTO {

    private long k_number;
    private String k_name;
    private String k_email;

    public KakaoDTO(Long k_number, String k_name, String k_email){
        this.k_number = k_number;
        this.k_name = k_name;
        this.k_email = k_email;
    }
}
