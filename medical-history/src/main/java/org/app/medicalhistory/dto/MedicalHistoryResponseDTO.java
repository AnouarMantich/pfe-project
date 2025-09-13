package org.app.medicalhistory.dto;


import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;


@Data @AllArgsConstructor  @NoArgsConstructor @Builder
public class MedicalHistoryResponseDTO {

    private UUID id;
    private String patientId;
    private List<String> knownConditions;
    private List<String> allergies;
    private List<MedicationDTO> medications;
    private List<SurgeryDTO> surgeries;
    private List<String> familyHistory;
    private LifestyleDTO lifestyle;
    private LocalDateTime lastUpdated;

    // ✅ Liste des consultations liées au patient
    private List<ConsultationResponse> consultations;

    // Getters et setters
    @Data @AllArgsConstructor  @NoArgsConstructor @Builder
    public static class MedicationDTO {
        private String name;
        private String dosage;
        private String frequency;

        // Getters et setters
    }
    @Data @AllArgsConstructor  @NoArgsConstructor @Builder
    public static class SurgeryDTO {
        private String name;
        private LocalDate date;

        // Getters et setters
    }
    @Data @AllArgsConstructor  @NoArgsConstructor @Builder
    public static class LifestyleDTO {
        private boolean smoker;
        private String alcohol;
        private String activityLevel;

        // Getters et setters
    }
}