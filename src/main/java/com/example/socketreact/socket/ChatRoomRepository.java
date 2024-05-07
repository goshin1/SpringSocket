package com.example.socketreact.socket;

import org.springframework.web.socket.WebSocketSession;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ChatRoomRepository {
    Map<String, ChatRoom> chatRoomMap = new HashMap<String, ChatRoom>();

    public static Collection<ChatRoom> chatRooms;

    public ChatRoomRepository(){
        for(int i = 0; i < 2; i++){
            String uuid = UUID.randomUUID().toString();
            ChatRoom chatRoom = new ChatRoom(uuid);
            chatRoomMap.put(chatRoom.getId(), chatRoom);
            System.out.println("ChatRoom클래스 복제 중");
            System.out.println("Chat Room ->"+chatRoom.toString());
        }
        chatRooms = chatRoomMap.values();
    }

    public ChatRoom getChatRoom(String id){ return chatRoomMap.get(id); }

    public Map<String, ChatRoom> getChatRooms() { return chatRoomMap; }

    public void remove(WebSocketSession session){
        this.chatRooms.parallelStream().forEach(chatRoom -> chatRoom.remove(session));
    }
}
