package com.example.agent.controller;

import com.example.agent.dto.ChunkResponseDTO;
import com.example.agent.service.ChatService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Flux;

@RequestMapping("/api/chat")
@RestController
public class ChatController {
    @Autowired
    private ChatService chatService;

    @GetMapping(value = "/stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<ChunkResponseDTO> streamChat(
            @RequestParam String question,
            @RequestParam String chatId) {
        return chatService.chatStream(question, chatId);
    }

    @GetMapping("/generate-chat-id")
    public String chat() {
        return String.valueOf(Math.random());
    }
}