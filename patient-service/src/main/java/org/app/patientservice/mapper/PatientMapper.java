package org.app.patientservice.mapper;

import lombok.RequiredArgsConstructor;
import org.app.patientservice.client.UserService;
import org.app.patientservice.dto.PatientRequestDTO;
import org.app.patientservice.dto.PatientResponseDTO;
import org.app.patientservice.dto.UserResponseDTO;
import org.app.patientservice.entity.Patient;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class PatientMapper {

    private final UserService userService;

    public Patient toPatient(PatientRequestDTO patientRequestDTO) {
        Patient patient = new Patient();
        BeanUtils.copyProperties(patientRequestDTO, patient);
        return patient;
    }


    public PatientResponseDTO toPatientResponseDTO(Patient patient) {
        PatientResponseDTO patientResponseDTO = new PatientResponseDTO();
        BeanUtils.copyProperties(patient, patientResponseDTO);
        UserResponseDTO userByCin = userService.getUserByCin(patientResponseDTO.getCin());
        patientResponseDTO.setUser(userByCin);
        return patientResponseDTO;
    }

}
