package com.example.report_service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

import static com.example.report_service.config.Constants.BASE_URL_ORDER;

@Configuration
public class ConfigurationClass {

    @Bean
    WebClient webClient() {
        return WebClient.builder()
                .baseUrl(BASE_URL_ORDER)
                .build();
    }

}
