package com.example.socketreact.controller;

import com.example.socketreact.dto.SocketDto;
import com.example.socketreact.entity.SocketEntity;
import com.example.socketreact.repository.SocketRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpRequest;
import java.util.List;


@RequestMapping("/socket")
@RestController
public class SocketController {
    @Autowired
    private SocketRepository socketRepository;

    @GetMapping("/test")
    @ResponseBody
    public Test test(){
        Test t = new Test(1, "테스트");
        return t;
    }

    @GetMapping("/save")
    public void save(@RequestParam String msg, @RequestParam String room, @RequestParam String user){
        System.out.println("msg : "+msg);
        socketRepository.save(new SocketEntity(null, user, room,  msg));
    }

    @GetMapping("/chatlist")
    @ResponseBody
    public List<SocketEntity> returnChats(@RequestParam String room){
        System.out.println(room);
        List<SocketEntity> chats = socketRepository.findRoomAll(room);
        return chats;
    }

}
