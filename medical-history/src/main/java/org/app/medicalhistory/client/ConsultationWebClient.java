package org.app.medicalhistory.client;

import lombok.RequiredArgsConstructor;
import org.app.medicalhistory.dto.ConsultationResponse;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ConsultationWebClient {

    @Qualifier("consultationServiceWebClient") // ✅ faire correspondre le nom
    private final WebClient consultationWebClient;

    public List<ConsultationResponse> getConsultationsByPatientId(String patientId) {
        return consultationWebClient
                .get()
                .uri("/api/v1/consultations/patient/{patientId}", patientId)
                .retrieve()
                .bodyToFlux(ConsultationResponse.class)
                .collectList()
                .block(); // bloquant : OK pour un service synchrone
    }
}
