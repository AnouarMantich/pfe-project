package org.app.patientservice.dto;

import lombok.Data;
import org.app.patientservice.enums.Gender;

import java.time.LocalDate;
import java.time.LocalDateTime;


@Data
public class PatientResponseDTO {

    private String cin;

    private UserResponseDTO user;

    private String emergencyContactName;

    private String emergencyContactPhone;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

}
