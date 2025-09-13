package org.app.userservice.dto;

import lombok.Data;

@Data
public class PatientRequestDTO {

    private String cin;  // Référence vers User (UserService)

    private String emergencyContactName;

    private String emergencyContactPhone;
}
