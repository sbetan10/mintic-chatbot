package com.example.agent.dto;

import org.springframework.ai.tool.annotation.ToolParam;

public record SqlQueryRequest(
        @ToolParam(description = "La consulta SQL SELECT generada a partir de la pregunta del"
                + " usuario debe ser una instrucción SELECT segura.")
        String query) {
}
