package org.app.doctorservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.app.doctorservice.enums.Gender;


import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserResponseDTO {
    private String cin;
    private String firstName;
    private String lastName;
    private String email;
    private String telephone;
    private LocalDate birthDate;
    private Gender gender;
    private String address;
}
