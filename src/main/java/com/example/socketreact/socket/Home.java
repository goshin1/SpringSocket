package com.example.socketreact.socket;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Collection;
import java.util.Iterator;

@CrossOrigin
@RequestMapping("/socket")
@Controller
public class Home {
    @GetMapping("/home")
    @ResponseBody
    public Collection<ChatRoom> homeController(HttpServletRequest request){
        Collection<ChatRoom> chatRooms = ChatRoomRepository.chatRooms;

        Iterator<ChatRoom> it = chatRooms.iterator();
        while(it.hasNext()) {
            ChatRoom chatRoom = it.next();
            System.out.println("ChatRoom Sessios" + chatRoom.getId() + " : " + chatRoom.getClass());
        }

        return chatRooms;
    }
}
