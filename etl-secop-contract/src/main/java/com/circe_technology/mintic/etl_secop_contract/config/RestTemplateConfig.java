package com.circe_technology.mintic.etl_secop_contract.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

/**
 * Configuration class to define and configure the RestTemplate bean.
 */
@Configuration
public class RestTemplateConfig {

    /**
     * Creates and configures a RestTemplate bean.
     * Headers are set here, or you can use a RequestFactory/Interceptor for more complex setup.
     */
    @Bean
    public RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate();

        // Optional: Set default headers, although it's often better to set them per request
        // or use an interceptor if needed across multiple requests.
        // For simple setup:
        // HttpHeaders headers = new HttpHeaders();
        // headers.set("User-Agent", "insomnia/11.6.1");
        // restTemplate.setInterceptors(Collections.singletonList((request, body, execution) -> {
        //     request.getHeaders().addAll(headers);
        //     return execution.execute(request, body);
        // }));

        return restTemplate;
    }
}