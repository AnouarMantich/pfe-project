package org.app.doctorservice.entity;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Doctor {

    @Id
    private String cin;  // Corresponds to the user ID from user-service

    @Column(nullable = false)
    private String specialty;

    @Column(nullable = false, unique = true)
    private String licenseNumber; // Professional registration number (e.g., RPPS)

    @Column(nullable = false)
    private String hospital;

    @Column(nullable = false)
    private String workPhone;

    private String workAddress;

    private Boolean available;

    private int yearsOfExperience;

}
