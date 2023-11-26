package com.example.chat.Repository;

import com.example.chat.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface MemberRepository extends JpaRepository <User,Long>{

    @Query(value = "SELECT is_admin from kakao_table where k_number=?1", nativeQuery= true)
    int isAdmin_check(String kakaoId);

    @Query(value = "SELECT k_name from kakao_table where k_number=?1", nativeQuery = true)
    String k_name_select(String kakaoId);
}
