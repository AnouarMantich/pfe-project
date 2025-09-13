package org.app.medicalhistory.entity;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data @NoArgsConstructor
@AllArgsConstructor @Builder
public class Medication {
    private String name;
    private String dosage;      // e.g., "500mg"
    private String frequency;   // e.g., "2x per day"

}