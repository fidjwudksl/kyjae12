package com.example.signup.chat;

import lombok.Data;

@Data
public class ChatMessage {
    private String roomId;
    private String sender;
    private String message;
}