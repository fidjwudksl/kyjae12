package com.example.signup.chat;

import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class ChatRoomRepository {

    private final Map<String, ChatRoom> chatRooms = new LinkedHashMap<>();

    public List<ChatRoom> findAllRoom() {
        return new ArrayList<>(chatRooms.values());
    }

    public ChatRoom findRoomById(String id) {
        return chatRooms.get(id);
    }

    public ChatRoom createChatRoom(String name) {
        String roomId = UUID.randomUUID().toString();
        ChatRoom room = new ChatRoom();
        room.setId(roomId);
        room.setName(name);
        chatRooms.put(roomId, room);
        return room;
    }
}