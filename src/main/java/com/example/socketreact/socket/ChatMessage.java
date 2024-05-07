package com.example.socketreact.socket;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ChatMessage {
    private String chatRoomId;
    private String message;
    private String type;
    private String user;
    private String room;
}
