package org.app.consultationservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.app.consultationservice.dto.ConsultationRequestDTO;
import org.app.consultationservice.dto.ConsultationResponseDTO;
import org.app.consultationservice.entity.Consultation;
import org.app.consultationservice.exception.ConsultationNotFoundException;
import org.app.consultationservice.mapper.ConsultationMapper;
import org.app.consultationservice.repository.ConsultationRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class ConsultationServiceImpl implements ConsultationService {

    private final ConsultationRepository consultationRepository;

    @Override
    public ConsultationResponseDTO createConsultation(ConsultationRequestDTO dto) {
        try {
            Consultation entity = ConsultationMapper.toEntity(dto);
            log.info("Creating consultation {}", entity);
            Consultation saved = consultationRepository.save(entity);
            log.info("Created consultation {}", saved);
            return ConsultationMapper.toDto(saved);
        } catch (Exception e) {
            log.error("Error creating consultation", e);
            throw e;
        }
    }

    @Override
    public ConsultationResponseDTO getConsultationById(UUID id) {
        try {
            Consultation entity = consultationRepository.findById(id)
                    .orElseThrow(() -> new ConsultationNotFoundException("Consultation not found for ID: " + id));
            log.info("Retrieved consultation with ID: {}", id);
            return ConsultationMapper.toDto(entity);
        } catch (Exception e) {
            log.error("Error retrieving consultation with ID: {}", id, e);
            throw e;
        }
    }

    @Override
    public List<ConsultationResponseDTO> getConsultationsByPatientId(String patientId) {
        List<Consultation> consultations = consultationRepository.findByPatientId(patientId);
        return consultations.stream()
                .map(ConsultationMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public Page<ConsultationResponseDTO> getConsultationsByDoctorId(String doctorId, Pageable pageable) {
        return consultationRepository.findByDoctorId(doctorId, pageable)
                .map(ConsultationMapper::toDto);
    }

    @Override
    public ConsultationResponseDTO updateConsultation(UUID id, ConsultationRequestDTO dto) {
        Consultation existing = consultationRepository.findById(id)
                .orElseThrow(() -> new ConsultationNotFoundException("Consultation not found for ID: " + id));

        ConsultationMapper.updateEntityFromDto(dto, existing);
        Consultation updated = consultationRepository.save(existing);
        return ConsultationMapper.toDto(updated);
    }

    @Override
    public void deleteConsultation(UUID id) {
        Consultation existing = consultationRepository.findById(id)
                .orElseThrow(() -> new ConsultationNotFoundException("Consultation not found for ID: " + id));
        consultationRepository.delete(existing);
    }
}
