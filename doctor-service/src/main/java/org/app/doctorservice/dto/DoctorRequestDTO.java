package org.app.doctorservice.dto;

import jakarta.persistence.Id;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.app.doctorservice.validator.Creation;
import org.app.doctorservice.validator.Update;

@Data @NoArgsConstructor  @AllArgsConstructor  @Builder
public class DoctorRequestDTO {


    @Id
    @NotBlank(message = "CIN is required",groups = {Creation.class})
    private String cin;

    @NotBlank(message = "Specialty is required",groups = {Creation.class})
    @Size(min = 2, max = 50, message = "Specialty must be between 2 and 50 characters",groups = {Creation.class, Update.class})
    private String specialty;

    @NotBlank(message = "License number is required",groups = {Creation.class})
    @Size(min = 5, max = 30, message = "License number must be between 5 and 30 characters",groups = {Creation.class, Update.class})
    private String licenseNumber;

    @NotBlank(message = "Hospital is required",groups = {Creation.class})
    private String hospital;

    @NotBlank(message = "Work phone is required",groups = {Creation.class})
    @Pattern(
            regexp = "^(06|07)\\d{8}$",
            message = "Phone number must start with 06 or 07 and contain exactly 10 digits",
            groups = {Creation.class, Update.class}
    )
    private String workPhone;

    private String workAddress;

    private Boolean available;

}
