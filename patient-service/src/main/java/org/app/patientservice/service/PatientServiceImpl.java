package org.app.patientservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.app.patientservice.dto.PatientRequestDTO;
import org.app.patientservice.dto.PatientResponseDTO;
import org.app.patientservice.exception.PatientNotFoundException;
import org.app.patientservice.mapper.PatientMapper;
import org.app.patientservice.entity.Patient;
import org.app.patientservice.repository.PatientRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class PatientServiceImpl implements PatientService {

    private final PatientRepository patientRepository;
    private final PatientMapper patientMapper;



    @Override
    public List<PatientResponseDTO> getAllPatientsByName(String name, int page, int pageSize, String sortBy, String sortOrder) {

      return   patientRepository.findAll().stream().map(patientMapper::toPatientResponseDTO).toList();

    }

    @Override
    public PatientResponseDTO getPatientByCIN(String cin) throws PatientNotFoundException {

        Patient patient = patientRepository.findById(cin).orElseThrow(
                () -> new PatientNotFoundException("Patient with CIN " + cin + " not found")
        );
        return patientMapper.toPatientResponseDTO(patient);
    }


    @Override
    public PatientResponseDTO createPatient(PatientRequestDTO patientRequestDTO) {
        Patient patient = patientMapper.toPatient(patientRequestDTO);
        Patient savedPatient = patientRepository.save(patient);
        return patientMapper.toPatientResponseDTO(savedPatient);
    }

    @Override
    public PatientResponseDTO updatePatient(String cin, PatientRequestDTO patientRequestDTO) throws PatientNotFoundException {

        Patient patient = patientRepository.findById(cin).orElseThrow(
                () -> new PatientNotFoundException("Patient with id " + cin + " not found")
        );

        if (patientRequestDTO.getEmergencyContactName() != null) {
            patient.setEmergencyContactName(patientRequestDTO.getEmergencyContactName());
        }
        if (patientRequestDTO.getEmergencyContactPhone() != null) {
            patient.setEmergencyContactPhone(patientRequestDTO.getEmergencyContactPhone());
        }

        return patientMapper.toPatientResponseDTO(patientRepository.save(patient));
    }

    @Override
    public void deletePatient(String cin) {
        patientRepository.deleteById(cin);
    }
}
