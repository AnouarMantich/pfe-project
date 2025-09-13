package org.app.doctorservice.mapper;

import lombok.RequiredArgsConstructor;
import org.app.doctorservice.client.UserService;
import org.app.doctorservice.dto.DoctorRequestDTO;
import org.app.doctorservice.dto.DoctorResponseDTO;
import org.app.doctorservice.dto.UserResponseDTO;
import org.app.doctorservice.entity.Doctor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DoctorMapper {
    private final UserService userService;

    public Doctor toDoctor(DoctorRequestDTO doctorRequestDTO) {
        Doctor doctor = new Doctor();
        BeanUtils.copyProperties(doctorRequestDTO, doctor);
        return doctor;
    }

    public DoctorResponseDTO toDoctorRequestDTO(Doctor doctor) {
        DoctorResponseDTO doctorResponseDTO = new DoctorResponseDTO();
        BeanUtils.copyProperties(doctor, doctorResponseDTO);
        UserResponseDTO userByCin = userService.getUserByCin(doctorResponseDTO.getCin());
        doctorResponseDTO.setUserInfos(userByCin);
        return doctorResponseDTO;
    }

}
