package org.app.medicalhistory.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.app.medicalhistory.validation.OnCreate;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;


@Data @AllArgsConstructor
@NoArgsConstructor
public class MedicalHistoryRequestDTO {

    @NotNull(message = "Le patientId est obligatoire", groups = OnCreate.class)
    private String patientId;

    @NotNull(message = "La liste des conditions connues est obligatoire", groups = OnCreate.class)
    private List<@NotBlank(message = "Une condition connue ne peut pas être vide") String> knownConditions;

    @NotNull(message = "La liste des allergies est obligatoire", groups = OnCreate.class)
    private List<@NotBlank(message = "Une allergie ne peut pas être vide") String> allergies;

    @Valid
    @NotNull(message = "La liste des médicaments est obligatoire", groups = OnCreate.class)
    private List<MedicationDTO> medications;

    @Valid
    @NotNull(message = "La liste des chirurgies est obligatoire", groups = OnCreate.class)
    private List<SurgeryDTO> surgeries;

    @NotNull(message = "La liste des antécédents familiaux est obligatoire", groups = OnCreate.class)
    private List<@NotBlank(message = "Un antécédent familial ne peut pas être vide") String> familyHistory;

    @Valid
    @NotNull(message = "Le style de vie est obligatoire", groups = OnCreate.class)
    private LifestyleDTO lifestyle;

    @NotNull(message = "La date de dernière mise à jour est obligatoire", groups = OnCreate.class)
    @PastOrPresent(message = "La date de mise à jour doit être passée ou présente")
    private LocalDateTime lastUpdated;

    // Getters et setters...

    @Data @AllArgsConstructor
    @NoArgsConstructor
    public static class MedicationDTO {
        @NotBlank(message = "Le nom du médicament est obligatoire", groups = OnCreate.class)
        private String name;

        @NotBlank(message = "Le dosage est obligatoire", groups = OnCreate.class)
        private String dosage;

        @NotBlank(message = "La fréquence est obligatoire", groups = OnCreate.class)
        private String frequency;

        // getters/setters
    }

    @Data @AllArgsConstructor
    @NoArgsConstructor
    public static class SurgeryDTO {
        @NotBlank(message = "Le nom de la chirurgie est obligatoire", groups = OnCreate.class)
        private String name;

        @NotNull(message = "La date de chirurgie est obligatoire", groups = OnCreate.class)
        @PastOrPresent(message = "La date de chirurgie doit être passée ou présente")
        private LocalDate date;

        // getters/setters
    }

    @Data @AllArgsConstructor
    @NoArgsConstructor
    public static class LifestyleDTO {
        private boolean smoker;

        @NotBlank(message = "Le statut d'alcool est obligatoire", groups = OnCreate.class)
        private String alcohol;

        @NotBlank(message = "Le niveau d'activité est obligatoire", groups = OnCreate.class)
        private String activityLevel;

        // getters/setters
    }
}

