package org.app.patientservice.service;

import org.app.patientservice.dto.PatientRequestDTO;
import org.app.patientservice.dto.PatientResponseDTO;
import org.app.patientservice.exception.PatientNotFoundException;

import java.util.List;

public interface PatientService {

    List<PatientResponseDTO> getAllPatientsByName(String name,int page,int pageSize,String sortBy,String sortOrder);
    PatientResponseDTO getPatientByCIN(String cin) throws PatientNotFoundException;
    PatientResponseDTO createPatient(PatientRequestDTO patientRequestDTO);
    PatientResponseDTO updatePatient(String id,PatientRequestDTO patientRequestDTO) throws PatientNotFoundException;
    void deletePatient(String id);

}
