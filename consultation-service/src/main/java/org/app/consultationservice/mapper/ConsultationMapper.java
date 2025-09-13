package org.app.consultationservice.mapper;

import org.app.consultationservice.dto.ConsultationRequestDTO;
import org.app.consultationservice.dto.ConsultationResponseDTO;
import org.app.consultationservice.entity.Consultation;

public class ConsultationMapper {

    public static Consultation toEntity(ConsultationRequestDTO dto) {
        Consultation entity = new Consultation();
        entity.setPatientId(dto.getPatientId());
        entity.setDoctorId(dto.getDoctorId());
        entity.setDate(dto.getDate());
        entity.setSymptoms(dto.getSymptoms());
        entity.setDiagnosis(dto.getDiagnosis());
        entity.setNotes(dto.getNotes());
        entity.setPrescriptions(dto.getPrescriptions());
        return entity;
    }

    public static ConsultationResponseDTO toDto(Consultation entity) {
        ConsultationResponseDTO dto = new ConsultationResponseDTO();
        dto.setId(entity.getId());
        dto.setPatientId(entity.getPatientId());
        dto.setDoctorId(entity.getDoctorId());
        dto.setDate(entity.getDate());
        dto.setSymptoms(entity.getSymptoms());
        dto.setDiagnosis(entity.getDiagnosis());
        dto.setNotes(entity.getNotes());
        dto.setPrescriptions(entity.getPrescriptions());
        return dto;
    }

    public static void updateEntityFromDto(ConsultationRequestDTO dto, Consultation entity) {
        if (dto.getDate() != null) entity.setDate(dto.getDate());
        if (dto.getSymptoms() != null) entity.setSymptoms(dto.getSymptoms());
        if (dto.getDiagnosis() != null) entity.setDiagnosis(dto.getDiagnosis());
        if (dto.getNotes() != null) entity.setNotes(dto.getNotes());
        if (dto.getPrescriptions() != null) entity.setPrescriptions(dto.getPrescriptions());
    }
}
