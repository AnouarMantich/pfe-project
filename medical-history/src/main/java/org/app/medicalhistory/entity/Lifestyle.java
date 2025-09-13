package org.app.medicalhistory.entity;


import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data @NoArgsConstructor
@AllArgsConstructor @Builder
public class Lifestyle {
    private boolean smoker;
    private String alcohol;         // e.g., "Never", "Occasional", "Regular"
    private String activityLevel;   // e.g., "Sedentary", "Moderate", "Active"
}
