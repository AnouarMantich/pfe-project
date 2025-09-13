package org.app.doctorservice.client;

import lombok.RequiredArgsConstructor;
import org.app.doctorservice.dto.UserResponseDTO;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

@Service
@RequiredArgsConstructor
public class UserService {

    private final WebClient webClient;

    public UserResponseDTO getUserByCin(String cin) {
        if (cin == null || cin.trim().isEmpty()) {
            throw new IllegalArgumentException("Le CIN ne peut pas être vide.");
        }

        try {
            return webClient.get()
                    .uri(uriBuilder -> uriBuilder.path("/{cin}").build(cin))
                    .accept(MediaType.APPLICATION_JSON)
                    .retrieve()
                    .onStatus(status -> status.is4xxClientError() || status.is5xxServerError(),
                            response -> response.bodyToMono(String.class)
                                    .map(errorBody -> new RuntimeException("Erreur HTTP : " + errorBody)))
                    .bodyToMono(UserResponseDTO.class)
                    .block();  // ⚠️ bloquant, OK en contexte synchrone

        } catch (WebClientResponseException e) {
            throw new RuntimeException("Erreur HTTP (" + e.getStatusCode() + ") : " + e.getResponseBodyAsString(), e);
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de l'appel de l'API utilisateur", e);
        }
    }

}
