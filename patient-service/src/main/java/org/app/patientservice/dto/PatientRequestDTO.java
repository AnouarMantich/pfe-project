package org.app.patientservice.dto;

import lombok.Data;

@Data
public class PatientRequestDTO {

    private String cin;  // Référence vers User (UserService)

    private String emergencyContactName;

    private String emergencyContactPhone;
}
