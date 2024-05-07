package com.example.socketreact.socket;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@Controller
@RequestMapping("/socket")
public class Room {
    @GetMapping("/room/{id}")
    @ResponseBody
    public String roomController(@PathVariable String id){
        return id;
    };

}
