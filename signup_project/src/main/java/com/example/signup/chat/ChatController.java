package com.example.signup.chat;

import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.HtmlUtils;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/chat")
public class ChatController {

    private final ChatRoomRepository chatRoomRepository;

    @GetMapping("/rooms")
    public List<ChatRoom> rooms() {
        return chatRoomRepository.findAllRoom();
    }

    @PostMapping("/room")
    public ChatRoom createRoom(@RequestParam String name) {
        return chatRoomRepository.createChatRoom(name);
    }

    @MessageMapping("/message")
    @SendTo("/topic/messages")
    public ChatMessage message(ChatMessage message) {
        message.setMessage(HtmlUtils.htmlEscape(message.getMessage()));
        return message;
    }
}