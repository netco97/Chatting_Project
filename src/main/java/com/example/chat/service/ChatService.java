package com.example.chat.service;

import com.example.chat.Repository.ChatRepository;
import com.example.chat.dto.ChatMessage;
import com.example.chat.dto.ChatRoom;
import com.example.chat.entity.ChatEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.*;

@RequiredArgsConstructor
@Service
public class ChatService {
    private Map<String, ChatRoom> chatRoomMap;
    private final ChatRepository chatRepository;

    @PostConstruct
    private void init(){
        chatRoomMap = new LinkedHashMap<>();
    }

    public List<ChatRoom>  findAllRoom(){
        // 채팅방 생성순서 최근 순으로 반환
        List chatRooms = new ArrayList<>(chatRoomMap.values());
        Collections.reverse(chatRooms);
        return chatRooms;
    }

    public ChatRoom findRoomById(String id){
        return chatRoomMap.get(id);
    }

    public ChatRoom createChatRoom(String name){
        ChatRoom chatRoom = ChatRoom.create(name);
        chatRoomMap.put(chatRoom.getRoomId(), chatRoom);
        return chatRoom;
    }

    public void save(String roomId, String sender, String message){
        // message (json) to DB(Entity)
        ChatMessage chatMessage = ChatMessage.
                builder()
                .roomId(roomId)
                .sender(sender)
                .message(message).build();

        ChatEntity chatEntity = ChatEntity.toChatEntity(chatMessage);
        chatRepository.save(chatEntity);
    }
}