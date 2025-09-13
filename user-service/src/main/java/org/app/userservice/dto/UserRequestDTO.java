package org.app.userservice.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.app.userservice.enums.Gender;
import org.app.userservice.groupeValidator.OnCreate;
import org.app.userservice.groupeValidator.OnUpdate;

import java.time.LocalDate;

@Data @AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserRequestDTO {

    @NotBlank(groups = {OnCreate.class}, message = "Le CIN est obligatoire")
    @Size(min = 5, message = "Le CIN doit avoir au moins 5 charactaire !", groups = {OnCreate.class, OnUpdate.class})
    private String cin;

    @NotBlank(groups = {OnCreate.class}, message = "Le prénom est obligatoire")
    private String firstName;

    @NotBlank(groups = {OnCreate.class}, message = "Le nom est obligatoire")
    private String lastName;

    @Email(message = "Email invalide", groups = {OnCreate.class})
    private String email;

    @Size(min = 10, max = 10, message = "Le numéro de telephone doit contenir exactement 10 chiffres 0*********", groups = {OnCreate.class, OnUpdate.class})
    private String telephone;

    @Past(message = "La date de naissance doit être dans le passé", groups = {OnCreate.class, OnUpdate.class})
    private LocalDate birthDate;

    private Gender gender;

    @NotBlank(message = "L'adresse est obligatoire", groups = {OnCreate.class})
    @Size(min = 10, max = 255, message = "L'adresse doit contenir entre 10 et 255 caractères", groups = {OnCreate.class, OnUpdate.class})
    private String address;

    @NotNull(message = "L'ID Keycloak est obligatoire pour la mise à jour", groups = {OnCreate.class})
    private String keycloakId;
}
