package org.app.medicalhistory.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    @Value("${consultation.service.url}")
    private String consultationServiceUrl;

    @Bean(name = "consultationServiceWebClient") // ✅ NOM UNIQUE
    public WebClient consultationWebClient() {
        return WebClient.builder()
                .baseUrl(consultationServiceUrl)
                .build();
    }
}
