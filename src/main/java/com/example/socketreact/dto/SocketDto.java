package com.example.socketreact.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SocketDto {
    private Long id;
    private String user;
    private String room;
    private String msg;
}
