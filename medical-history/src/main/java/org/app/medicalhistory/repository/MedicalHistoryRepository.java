package org.app.medicalhistory.repository;

import org.app.medicalhistory.entity.MedicalHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface MedicalHistoryRepository extends JpaRepository<MedicalHistory, UUID> {
    Optional<MedicalHistory> findByPatientId(String patientId);
}
