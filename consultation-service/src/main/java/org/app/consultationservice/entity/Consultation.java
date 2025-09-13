package org.app.consultationservice.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Data @NoArgsConstructor
@AllArgsConstructor
public class Consultation {

    @Id
    @GeneratedValue
    private UUID id;

    @NotNull
    private String patientId; // Référence au patient (UUID ou string)

    @NotNull
    private String doctorId;  // Référence au médecin (UUID ou string)

    @NotNull
    private LocalDateTime date;

    @NotBlank
    private String symptoms;

    private String diagnosis;

    private String notes;

    @ElementCollection
    private List<String> prescriptions = new ArrayList<>();


}
