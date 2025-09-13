package org.app.doctorservice.service;

import lombok.RequiredArgsConstructor;
import org.app.doctorservice.depository.DoctorRepository;
import org.app.doctorservice.dto.DoctorRequestDTO;
import org.app.doctorservice.dto.DoctorResponseDTO;
import org.app.doctorservice.exception.DoctorNotFoundException;
import org.app.doctorservice.mapper.DoctorMapper;
import org.app.doctorservice.entity.Doctor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional
@RequiredArgsConstructor
public class DoctorServiceImpl implements DoctorService {

    private final DoctorRepository doctorRepository;
    private final DoctorMapper doctorMapper;

    @Override
    public List<DoctorResponseDTO> getAllDoctors(int page, int size, String sortBy, String sortDirection) {
        Page<Doctor> allDoctorsPage =
                doctorRepository.findAll(PageRequest.of(page,size));

        return allDoctorsPage.getContent().stream().map(doctorMapper::toDoctorRequestDTO).toList();
    }

    @Override
    public DoctorResponseDTO getDoctorByCIN(String cin) throws DoctorNotFoundException {
        Doctor doctor = doctorRepository.findById(cin).orElseThrow(
                () -> new DoctorNotFoundException("")
        );
        return doctorMapper.toDoctorRequestDTO(doctor);
    }

    @Override
    public DoctorResponseDTO createDoctor(DoctorRequestDTO doctorRequestDTO) {
        Doctor doctor = doctorMapper.toDoctor(doctorRequestDTO);
        doctor = doctorRepository.save(doctor);
        return doctorMapper.toDoctorRequestDTO(doctor);
    }

    @Override
    public DoctorResponseDTO updateDoctor(DoctorRequestDTO doctorRequestDTO, String cin) throws DoctorNotFoundException {
        Doctor doctor = doctorRepository.findById(cin).orElseThrow(
                () -> new DoctorNotFoundException("")
        );
        if (doctorRequestDTO.getCin()!=null) {
            doctor.setCin(doctorRequestDTO.getCin());
        }
        if (doctorRequestDTO.getSpecialty()!=null) {
            doctor.setSpecialty(doctorRequestDTO.getSpecialty());
        }
       if (doctorRequestDTO.getLicenseNumber()!=null) {
           doctor.setLicenseNumber(doctorRequestDTO.getLicenseNumber());
       }
       if (doctorRequestDTO.getHospital()!=null) {
           doctor.setHospital(doctorRequestDTO.getHospital());
       }

       if (doctorRequestDTO.getWorkPhone()!=null) {
           doctor.setWorkPhone(doctorRequestDTO.getWorkPhone());
       }
       if (doctorRequestDTO.getWorkAddress()!=null) {
           doctor.setWorkAddress(doctorRequestDTO.getWorkAddress());
       }

       if (doctorRequestDTO.getAvailable() !=null ){
           doctor.setAvailable(doctorRequestDTO.getAvailable());
       }


       doctor = doctorRepository.save(doctor);

        return doctorMapper.toDoctorRequestDTO(doctor);
    }

    @Override
    public void deleteDoctor(String cin) {
        doctorRepository.deleteById(cin);
    }
}
