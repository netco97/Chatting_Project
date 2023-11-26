package com.example.chat.Repository;

import com.example.chat.dto.ChatDTO;
import com.example.chat.dto.ChatDTO2;
import com.example.chat.entity.ChatEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ChatRepository extends JpaRepository<ChatEntity,Long> {

    //SELECT * from chat_table where room_Id =? order by id desc limit 10
    List<ChatDTO> findTop30ByRoomIdAndIsProOrderByIdDesc(String roomId, String isPro);

    //List<ChatDTO> findByIsProAndRoomId(String isPro, String roomId, Pageable pageable);

    @Query(value = "SELECT id,date,is_pro as isPro,kakao_id as kakaoId ,msg,room_id as roomId,sender FROM chat_table WHERE is_pro=?1 and room_id=?2 order by date desc LIMIT ?3 OFFSET ?4", nativeQuery = true)
    List<ChatDTO2> findDataWithLimitAndOffset
            (String isPro, String roomId, int limit, int offset);

    /*@Query(value = "SELECT * FROM CHAT_TABLE where is_pro=?1 and room_id =?2 order by date desc LIMIT 30 OFFSET ?3", nativeQuery = true)
    List<PrevLoadDTO> sel_proDTO(String isPro, String roomId, int count);*/
    //List<ChatDTO> findTop10ByRoomIdAndIdLessThanOrderByIdDesc(String roomId,Long id);

    Long countByRoomId(String roomId);

    Long countByKakaoId(String kakaoId);
}
