package org.app.userservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.app.userservice.enums.Gender;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "users")
@Data @NoArgsConstructor
@AllArgsConstructor @Builder
public class User {
    @Id
    private String cin;
    private String firstName;
    private String lastName;
    private String email;
    private String telephone;
    private LocalDate birthDate;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    private String address;
    private String keycloakId;

    @CreationTimestamp
    private LocalDateTime creationTimestamp;
    @UpdateTimestamp
    private LocalDateTime lastModificationTimestamp;
}
