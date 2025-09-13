package org.app.medicalhistory.service;


import org.app.medicalhistory.dto.MedicalHistoryRequestDTO;
import org.app.medicalhistory.dto.MedicalHistoryResponseDTO;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface MedicalHistoryService {

    /**
     * Créer un nouvel historique médical
     */
    MedicalHistoryResponseDTO createMedicalHistory(MedicalHistoryRequestDTO dto);

    /**
     * Récupérer un historique par son ID
     */
    MedicalHistoryResponseDTO getMedicalHistoryById(UUID id);

    /**
     * Récupérer l’historique d’un patient
     */
    MedicalHistoryResponseDTO getMedicalHistoryByPatientId(String patientId);

    /**
     * Mettre à jour un historique existant
     */
    MedicalHistoryResponseDTO updateMedicalHistory(UUID id, MedicalHistoryRequestDTO dto);

    /**
     * Supprimer un historique médical
     */
    void deleteMedicalHistory(UUID id);

    /**
     * Recherche par critères (optionnelle)
     */
    List<MedicalHistoryResponseDTO> searchMedicalHistories(String condition, Boolean smoker);
}
