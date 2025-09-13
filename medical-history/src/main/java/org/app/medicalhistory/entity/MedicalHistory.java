package org.app.medicalhistory.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Data @NoArgsConstructor
@AllArgsConstructor @Builder
public class MedicalHistory {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String patientId;

    @ElementCollection
    private List<String> knownConditions;

    @ElementCollection
    private List<String> allergies;

    @ElementCollection
    private List<Medication> medications;

    @ElementCollection
    private List<Surgery> surgeries;

    @ElementCollection
    private List<String> familyHistory;

    @Embedded
    private Lifestyle lifestyle;

    private LocalDateTime lastUpdated;
}
