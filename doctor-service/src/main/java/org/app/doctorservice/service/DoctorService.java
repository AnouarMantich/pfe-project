package org.app.doctorservice.service;

import org.app.doctorservice.dto.DoctorRequestDTO;
import org.app.doctorservice.dto.DoctorResponseDTO;
import org.app.doctorservice.exception.DoctorNotFoundException;

import java.util.List;

public interface DoctorService {
    List<DoctorResponseDTO> getAllDoctors(int page, int size, String sortBy, String sortDirection);

    DoctorResponseDTO getDoctorByCIN(String cin) throws DoctorNotFoundException;

    DoctorResponseDTO createDoctor(DoctorRequestDTO doctorRequestDTO);

    DoctorResponseDTO updateDoctor(DoctorRequestDTO doctorRequestDTO, String cin) throws DoctorNotFoundException;

    void deleteDoctor(String cin);
}
