package com.example.chat.entity;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseTimeEntity {

    //@CreatedDate
    //private LocalDateTime createDate;

    private String createdDate;

    @PrePersist
        //해당 엔티티 저장하기 전
    void onPrePersist(){
        this.createdDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }


    // 포맷이 원하는 형식으로 안나옴
    //private String createdDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
}
