package com.example.chat.Repository;

import com.example.chat.dto.ChatDTO;
import com.example.chat.entity.ChatEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ChatRepository extends JpaRepository<ChatEntity,Long> {
    /*@Query(value = "select message,sender from chat_table where room_Id=?1 order by id desc" , nativeQuery = true)
    List<ChatDTO> select_chat(String roomId);*/
}
