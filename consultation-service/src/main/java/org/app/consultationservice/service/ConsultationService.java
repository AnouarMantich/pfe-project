package org.app.consultationservice.service;

import org.app.consultationservice.dto.ConsultationRequestDTO;
import org.app.consultationservice.dto.ConsultationResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface ConsultationService {
    ConsultationResponseDTO createConsultation(ConsultationRequestDTO dto);
    ConsultationResponseDTO getConsultationById(UUID id);
    List<ConsultationResponseDTO> getConsultationsByPatientId(String patientId);


    Page<ConsultationResponseDTO> getConsultationsByDoctorId(String doctorId, Pageable pageable);

    ConsultationResponseDTO updateConsultation(UUID id, ConsultationRequestDTO dto);
    void deleteConsultation(UUID id);
}
