package com.example.chat.Repository;

import com.example.chat.dto.ChatDTO;
import com.example.chat.entity.ChatEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ChatRepository extends JpaRepository<ChatEntity,Long> {

    //SELECT * from chat_table where room_Id =? order by id desc limit 10
    List<ChatDTO> findTop30ByRoomIdAndIsProOrderByIdDesc(String roomId, String isPro);

    //List<ChatDTO> findTop10ByRoomIdAndIdLessThanOrderByIdDesc(String roomId,Long id);

    Long countByRoomId(String roomId);

    Long countByKakaoId(String kakaoId);
}
