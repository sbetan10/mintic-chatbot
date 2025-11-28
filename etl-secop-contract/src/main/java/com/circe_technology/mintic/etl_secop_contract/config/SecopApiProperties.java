package com.circe_technology.mintic.etl_secop_contract.config;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Configuration properties class to manage external API details.
 * Mapped to the 'secop.api' prefix in application.properties or application.yml.
 */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Configuration
@ConfigurationProperties(prefix = "secop.api")
public class SecopApiProperties {
    private String baseUrl;
    private String appToken;
    private int pageSize;
}
