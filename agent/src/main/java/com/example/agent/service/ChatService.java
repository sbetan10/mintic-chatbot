package com.example.agent.service;

import com.example.agent.dto.ChunkResponseDTO;
import com.example.agent.tools.AgentTools;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;

import reactor.core.publisher.Flux;

@Service
public class ChatService {

    @Autowired
    private ChatClient chatClient;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Flux<ChunkResponseDTO> chatStream(String question, String chatId) {
        return chatClient
                .prompt(question)
                .tools(new AgentTools(jdbcTemplate))
                .advisors(advisor -> advisor.param(ChatMemory.CONVERSATION_ID, chatId))
                .stream()
                .content()
                .map(ChunkResponseDTO::new)
                .timeout(Duration.ofMinutes(2));
    }
}
