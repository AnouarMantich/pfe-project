package org.app.medicalhistory.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.app.medicalhistory.Exception.MedicalHistoryNotFoundException;
import org.app.medicalhistory.client.ConsultationWebClient;
import org.app.medicalhistory.dto.ConsultationResponse;
import org.app.medicalhistory.dto.MedicalHistoryRequestDTO;
import org.app.medicalhistory.dto.MedicalHistoryResponseDTO;
import org.app.medicalhistory.entity.MedicalHistory;
import org.app.medicalhistory.entity.Medication;
import org.app.medicalhistory.entity.Surgery;
import org.app.medicalhistory.mapper.MedicalHistoryMapper;
import org.app.medicalhistory.repository.MedicalHistoryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.app.medicalhistory.mapper.MedicalHistoryMapper.*;


@Service
@Transactional
@RequiredArgsConstructor
public class MedicalHistoryServiceImpl implements MedicalHistoryService {

    private final MedicalHistoryRepository medicalHistoryRepository;
    private final ConsultationWebClient  consultationWebClient;

    @Override
    public MedicalHistoryResponseDTO createMedicalHistory(MedicalHistoryRequestDTO dto) {

        // Mapper le DTO vers l'entité
        MedicalHistory entity = MedicalHistoryMapper.toEntity(dto);

        // Sauvegarder l'entité dans la base
        MedicalHistory savedEntity = medicalHistoryRepository.save(entity);

        // Retourner le DTO de réponse basé sur l'entité persistée
        return MedicalHistoryMapper.toDto(savedEntity);
    }


    @Override
    public MedicalHistoryResponseDTO getMedicalHistoryById(UUID id) {
        MedicalHistory medicalHistory = medicalHistoryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Medical history not found for id: " + id));

        return MedicalHistoryMapper.toDto(medicalHistory);
    }

    @Override
    public MedicalHistoryResponseDTO getMedicalHistoryByPatientId(String patientId) {
        MedicalHistory history = medicalHistoryRepository.findByPatientId(patientId)
                .orElseThrow(() -> new MedicalHistoryNotFoundException("Medical history not found for patientId: " + patientId));

        MedicalHistoryResponseDTO dto = MedicalHistoryMapper.toDto(history);

        List<ConsultationResponse> consultations =
                consultationWebClient.getConsultationsByPatientId(patientId);

        dto.setConsultations(consultations);

        return dto;
    }



    @Override
    public MedicalHistoryResponseDTO updateMedicalHistory(UUID id, MedicalHistoryRequestDTO dto) {
        MedicalHistory existing = medicalHistoryRepository.findById(id)
                .orElseThrow(() -> new MedicalHistoryNotFoundException("Medical history not found for id: " + id));

        MedicalHistoryMapper.updateEntityFromDto(dto, existing);
        existing.setLastUpdated(LocalDateTime.now());

        MedicalHistory updated = medicalHistoryRepository.save(existing);
        return MedicalHistoryMapper.toDto(updated);
    }

    @Override
    public void deleteMedicalHistory(UUID id) {

    }

    @Override
    public List<MedicalHistoryResponseDTO> searchMedicalHistories(String condition, Boolean smoker) {
        return List.of();
    }


    public static void updateEntityFromDto(MedicalHistoryRequestDTO dto, MedicalHistory entity) {
        if (dto == null || entity == null) return;

        // Patient ID : tu peux le garder modifiable si nécessaire
        if (dto.getPatientId() != null)
            entity.setPatientId(dto.getPatientId());

        // Concaténation des knownConditions
        if (dto.getKnownConditions() != null) {
            List<String> existing = entity.getKnownConditions() != null
                    ? entity.getKnownConditions()
                    : new ArrayList<>();
            existing.addAll(dto.getKnownConditions());
            entity.setKnownConditions(existing);
        }

        // Concaténation des allergies
        if (dto.getAllergies() != null) {
            List<String> existing = entity.getAllergies() != null
                    ? entity.getAllergies()
                    : new ArrayList<>();
            existing.addAll(dto.getAllergies());
            entity.setAllergies(existing);
        }

        // Concaténation des familyHistory
        if (dto.getFamilyHistory() != null) {
            List<String> existing = entity.getFamilyHistory() != null
                    ? entity.getFamilyHistory()
                    : new ArrayList<>();
            existing.addAll(dto.getFamilyHistory());
            entity.setFamilyHistory(existing);
        }

        // Ajout des médicaments (écrasement ou fusion possible)
        if (dto.getMedications() != null) {
            List<Medication> existing = entity.getMedications() != null
                    ? entity.getMedications()
                    : new ArrayList<>();
            existing.addAll(toMedicationEntities(dto.getMedications()));
            entity.setMedications(existing);
        }

        // Ajout des chirurgies
        if (dto.getSurgeries() != null) {
            List<Surgery> existing = entity.getSurgeries() != null
                    ? entity.getSurgeries()
                    : new ArrayList<>();
            existing.addAll(toSurgeryEntities(dto.getSurgeries()));
            entity.setSurgeries(existing);
        }

        // Remplacement complet de lifestyle (car unique)
        if (dto.getLifestyle() != null) {
            entity.setLifestyle(toLifestyleEntity(dto.getLifestyle()));
        }

        // Mise à jour de la date
        if (dto.getLastUpdated() != null)
            entity.setLastUpdated(dto.getLastUpdated());
    }

}
