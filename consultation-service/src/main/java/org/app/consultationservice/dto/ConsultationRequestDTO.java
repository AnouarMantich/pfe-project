package org.app.consultationservice.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.app.consultationservice.validation.Create;

import java.time.LocalDateTime;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ConsultationRequestDTO {

    @NotNull(groups = {Create.class})
    private String patientId;

    @NotNull(groups = {Create.class})
    private String doctorId;

    @NotNull(groups = {Create.class})
    private LocalDateTime date;

    @NotBlank(groups = {Create.class})
    private String symptoms;

    private String diagnosis;

    private String notes;

    private List<String> prescriptions;
}
