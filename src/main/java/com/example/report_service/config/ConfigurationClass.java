package com.example.report_service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

import static com.example.report_service.config.Constants.BASE_URL_ORDER;
import static com.example.report_service.config.Constants.BASE_URL_ORDER_MINIKUBE;

@Configuration
public class ConfigurationClass {

    @Bean(name = "base_url_order")
    WebClient webClientOriginal() {
        return WebClient.builder()
                .baseUrl(BASE_URL_ORDER)
                .build();
    }

    @Bean(name = "base_url_order_minikube")
    WebClient webClientMinikube() {
        return WebClient.builder()
                .baseUrl(BASE_URL_ORDER_MINIKUBE)
                .build();
    }

}
