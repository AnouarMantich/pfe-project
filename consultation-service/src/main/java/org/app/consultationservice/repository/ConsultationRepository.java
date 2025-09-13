package org.app.consultationservice.repository;

import org.app.consultationservice.entity.Consultation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;
import java.util.UUID;

public interface ConsultationRepository extends JpaRepository<Consultation, UUID> {
    List<Consultation> findByPatientId(String patientId);
    Page<Consultation> findByDoctorId(String doctorId, Pageable pageable);
}
