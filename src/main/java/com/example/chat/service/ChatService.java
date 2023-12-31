package com.example.chat.service;

import com.example.chat.Repository.ChatRepository;
import com.example.chat.dto.*;
import com.example.chat.entity.ChatEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.text.SimpleDateFormat;
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
        System.out.println("chatRoom 확인 " + chatRoom.getName()+ "chatRoomMap : " + chatRoomMap);
        return chatRoom;
    }

    public void save(String roomId, String msg, String kakaoId, String date, String isPro, String sender){
        // message (json) to DB(Entity)
        ChatMessage chatMessage = ChatMessage.
                builder()
                .roomId(roomId)
                .kakaoId(kakaoId)
                .msg(msg)
                .date(date)
                .isPro(isPro)
                        .sender(sender).
                build();

        ChatEntity chatEntity = ChatEntity.toChatEntity(chatMessage);
        chatRepository.save(chatEntity);
    }

    public List<ChatDTO> proChatLoad(String roomId) {

        return chatRepository.findTop30ByRoomIdAndIsProOrderByIdDesc(roomId, "1");
    }

    public List<ChatDTO> conChatLoad(String roomId) {

        return chatRepository.findTop30ByRoomIdAndIsProOrderByIdDesc(roomId, "0");
    }

    public List<ChatDTO2> proPrevLoad(String roomId,String isPro, int count){
        //return chatRepository.findByIsProAndRoomId("1",roomId, pageable);
        return chatRepository.findDataWithLimitAndOffset(isPro,roomId, 30, count);
    }

    //이름 변경
    public String maskName(String name) {
        if (name == null || name.length() <= 1) {
            return name;
        }

        StringBuilder maskedName = new StringBuilder();

        if (name.length() == 2) {
            // 이름이 2글자인 경우, 첫 번째 글자를 제외한 나머지를 ●로 바꿈
            maskedName.append(name.charAt(0));
            maskedName.append("●");
        } else {
            // 이름이 3글자 이상인 경우, 첫 번째와 마지막 글자를 제외하고 나머지를 ●로 바꿈
            maskedName.append(name.charAt(0));
            for (int i = 1; i < name.length() - 1; i++) {
                maskedName.append("●");
            }
            maskedName.append(name.charAt(name.length() - 1));
        }

        return maskedName.toString();
    }

    //현재 시간계산
    public static String getCurrentDateTime() {
        // Get the current date and time
        Date currentDate = new Date();

        // Create a SimpleDateFormat object with the desired format
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        // Format the date and time
        return dateFormat.format(currentDate);
    }

    /*public List<ChatDTO> listChat(String roomId) {
        //List<ChatDTO> list = chatRepository.findByRoomIdOrderByIdDesc(roomId);

        return chatRepository.findTop10ByRoomIdOrderByIdDesc(roomId);
    }

    public List<ChatDTO> prevChat(String roomId, Long id) {

        return chatRepository.findTop10ByRoomIdAndIdLessThanOrderByIdDesc(roomId,id);
    }*/
}