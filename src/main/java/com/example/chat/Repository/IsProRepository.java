package com.example.chat.Repository;

import com.example.chat.entity.IsProEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface IsProRepository extends JpaRepository<IsProEntity,Long> {

    @Query(value = "select count(*) from is_pro_table where kakao_id=?1 and room_id=?2" , nativeQuery = true)
    int duplicate_check(String kakaoId,String roomId);

    @Query(value = "SELECT is_pro from is_pro_table where kakao_id=?1 and room_id=?2", nativeQuery= true)
    int isPro_check(String kakaoId, String roomId);

    @Query(value = "SELECT count(is_pro) from is_pro_table where kakao_id=?1 and room_id=?2", nativeQuery= true)
    int isPro_count_check(String kakaoId, String roomId);

    Long countByKakaoId(String kakaoId);
}