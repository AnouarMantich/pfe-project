package org.app.medicalhistory.mapper;


import org.app.medicalhistory.dto.MedicalHistoryRequestDTO;
import org.app.medicalhistory.dto.MedicalHistoryResponseDTO;
import org.app.medicalhistory.entity.Lifestyle;
import org.app.medicalhistory.entity.MedicalHistory;
import org.app.medicalhistory.entity.Medication;
import org.app.medicalhistory.entity.Surgery;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MedicalHistoryMapper {

    // -----------------------------
    // RequestDTO -> Entity
    // -----------------------------
    public static MedicalHistory toEntity(MedicalHistoryRequestDTO dto) {
        if (dto == null) return null;

        MedicalHistory entity = new MedicalHistory();

        entity.setPatientId(dto.getPatientId());
        entity.setKnownConditions(dto.getKnownConditions());
        entity.setAllergies(dto.getAllergies());
        entity.setMedications(toMedicationEntities(dto.getMedications()));
        entity.setSurgeries(toSurgeryEntities(dto.getSurgeries()));
        entity.setFamilyHistory(dto.getFamilyHistory());
        entity.setLifestyle(toLifestyleEntity(dto.getLifestyle()));
        entity.setLastUpdated(dto.getLastUpdated());

        return entity;
    }

    // -----------------------------
    // Entity -> ResponseDTO
    // -----------------------------
    public static MedicalHistoryResponseDTO toDto(MedicalHistory entity) {
        if (entity == null) return null;

        MedicalHistoryResponseDTO dto = new MedicalHistoryResponseDTO();
        dto.setId(entity.getId());
        dto.setPatientId(entity.getPatientId());
        dto.setKnownConditions(entity.getKnownConditions());
        dto.setAllergies(entity.getAllergies());
        dto.setMedications(toMedicationDtos(entity.getMedications()));
        dto.setSurgeries(toSurgeryDtos(entity.getSurgeries()));
        dto.setFamilyHistory(entity.getFamilyHistory());
        dto.setLifestyle(toLifestyleDto(entity.getLifestyle()));
        dto.setLastUpdated(entity.getLastUpdated());

        return dto;
    }

    // -----------------------------
    // Partial update from DTO
    // -----------------------------
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

    // ============================
    // Mappers: DTO <-> Entity
    // ============================

    public static List<Medication> toMedicationEntities(List<MedicalHistoryRequestDTO.MedicationDTO> list) {
        return list == null ? null : list.stream().map(dto -> {
            Medication m = new Medication();
            m.setName(dto.getName());
            m.setDosage(dto.getDosage());
            m.setFrequency(dto.getFrequency());
            return m;
        }).collect(Collectors.toList());
    }

    private static List<MedicalHistoryResponseDTO.MedicationDTO> toMedicationDtos(List<Medication> list) {
        return list == null ? null : list.stream().map(m -> {
            MedicalHistoryResponseDTO.MedicationDTO dto = new MedicalHistoryResponseDTO.MedicationDTO();
            dto.setName(m.getName());
            dto.setDosage(m.getDosage());
            dto.setFrequency(m.getFrequency());
            return dto;
        }).collect(Collectors.toList());
    }

    public static List<Surgery> toSurgeryEntities(List<MedicalHistoryRequestDTO.SurgeryDTO> list) {
        return list == null ? null : list.stream().map(dto -> {
            Surgery s = new Surgery();
            s.setName(dto.getName());
            s.setDate(dto.getDate());
            return s;
        }).collect(Collectors.toList());
    }

    private static List<MedicalHistoryResponseDTO.SurgeryDTO> toSurgeryDtos(List<Surgery> list) {
        return list == null ? null : list.stream().map(s -> {
            MedicalHistoryResponseDTO.SurgeryDTO dto = new MedicalHistoryResponseDTO.SurgeryDTO();
            dto.setName(s.getName());
            dto.setDate(s.getDate());
            return dto;
        }).collect(Collectors.toList());
    }

    public static Lifestyle toLifestyleEntity(MedicalHistoryRequestDTO.LifestyleDTO dto) {
        if (dto == null) return null;
        Lifestyle lifestyle = new Lifestyle();
        lifestyle.setSmoker(dto.isSmoker());
        lifestyle.setAlcohol(dto.getAlcohol());
        lifestyle.setActivityLevel(dto.getActivityLevel());
        return lifestyle;
    }

    private static MedicalHistoryResponseDTO.LifestyleDTO toLifestyleDto(Lifestyle lifestyle) {
        if (lifestyle == null) return null;
        MedicalHistoryResponseDTO.LifestyleDTO dto = new MedicalHistoryResponseDTO.LifestyleDTO();
        dto.setSmoker(lifestyle.isSmoker());
        dto.setAlcohol(lifestyle.getAlcohol());
        dto.setActivityLevel(lifestyle.getActivityLevel());
        return dto;
    }
}