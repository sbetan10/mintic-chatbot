package com.example.agent.config;

import static com.example.agent.contants.Promt.defaultSystemPrompt;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ChatClientConfig {
    @Value("${spring.ai.openai.chat.options.model:gpt-4o-mini}")
    private String model;

    @Value("${spring.ai.openai.chat.options.temperature:0.2}")
    private double temperature;

    @Bean
    public ChatClient chatClient(ChatClient.Builder chatClientBuilder) {
        return chatClientBuilder
                .defaultOptions(OpenAiChatOptions.builder()
                        .model(model)
                        .build())
                .defaultSystem(defaultSystemPrompt)
                .build();
    }
}