package org.app.patientservice.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    @Value("${application.user.url}")
    private String BASE_URL;

    @Bean
    public WebClient webClient(WebClient.Builder builder) {
        return builder
                .baseUrl(BASE_URL) // URL de base (facultatif)
                .build();
    }
}
