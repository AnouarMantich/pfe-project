package org.app.consultationservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;


@Data @AllArgsConstructor @NoArgsConstructor
public class ConsultationResponseDTO {

    private UUID id;
    private String patientId;
    private String doctorId;
    private LocalDateTime date;
    private String symptoms;
    private String diagnosis;
    private String notes;
    private List<String> prescriptions;
}
