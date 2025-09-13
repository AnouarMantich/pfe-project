package org.app.medicalhistory.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
public  class ConsultationResponse {
    private UUID id;
    private String doctorId;
    private String symptoms;
    private String diagnosis;
    private String notes;
    private LocalDateTime date;
    private List<String> prescriptions;
}
