package org.app.medicalhistory.entity;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Embeddable
@Data @NoArgsConstructor  @AllArgsConstructor
public class Surgery {
    private String name;        // e.g., "Appendectomy"
    private LocalDate date;     // Surgery date
}
