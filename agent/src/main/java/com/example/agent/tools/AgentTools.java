package com.example.agent.tools;

import org.springframework.ai.tool.annotation.Tool;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.JdbcUtils;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
public class AgentTools {

    private final JdbcTemplate jdbcTemplate;

    public AgentTools(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Tool(description = "Obtiene la fecha y la hora actual")
    public String getCurrentDateTime() {
        return LocalDateTime.now().atZone(LocaleContextHolder.getTimeZone().toZoneId()).toString();
    }

    @Tool(name = "runSqlQueryTool", description =
            "Ejecuta una consulta **SEGURA** y **OPTIMA** SELECT SQL contra la tabla "
                    + "'contratos_secop_v2'"
                    + " para recuperar datos."
                    + " El parámetro de entrada 'query' debe ser una instrucción SQL SELECT "
                    + "válida.")
    public String runSqlQueryTool(String query) {
        try {
            log.info("SQL to execute: " + query);
            List<Map<String, Object>> results = jdbcTemplate.queryForList(query);

            if (results.isEmpty()) return "La consulta no retorno resultados.";

            StringBuilder csvBuilder = new StringBuilder();

            String header = results.getFirst().keySet().stream()
                    .map(JdbcUtils::convertUnderscoreNameToPropertyName)
                    .collect(Collectors.joining(", "));
            csvBuilder.append(header).append("\n");

            for (Map<String, Object> row : results) {
                String rowString = row.values().stream()
                        .map(Object::toString)
                        .collect(Collectors.joining(", "));
                csvBuilder.append(rowString).append("\n");
            }

            return csvBuilder.toString();
        } catch (Exception e) {
            log.error("Error ejecutando la consulta: " + e.getMessage());
            return "Error ejecutando la consulta: " + e.getMessage();
        }
    }
}