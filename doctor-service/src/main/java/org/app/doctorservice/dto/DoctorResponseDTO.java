package org.app.doctorservice.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class DoctorResponseDTO {

    private UserResponseDTO userInfos;
    private String cin;  // Corresponds to the user ID from user-service
    private String specialty;
    private String licenseNumber; // Professional registration number (e.g., RPPS)
    private String hospital;
    private String workPhone;
    private String workAddress;
    private Boolean available;
}
