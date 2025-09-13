package org.app.doctorservice.controller;

import lombok.RequiredArgsConstructor;
import org.app.doctorservice.dto.DoctorRequestDTO;
import org.app.doctorservice.dto.DoctorResponseDTO;
import org.app.doctorservice.exception.DoctorNotFoundException;
import org.app.doctorservice.service.DoctorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/doctors")
public class DoctorController {

    private final DoctorService doctorService;

    @GetMapping
    public ResponseEntity<List<DoctorResponseDTO>> getAllDoctors(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10")int size,
            @RequestParam(defaultValue = "cin")String sortBy,
            @RequestParam(defaultValue = "asc") String sortDirection) {
        return ResponseEntity.ok().body(doctorService.getAllDoctors(page,size,sortBy,sortDirection));
    }

    @GetMapping("/{cin}")
    public ResponseEntity<DoctorResponseDTO> getDoctorByCIN(@PathVariable String cin) throws DoctorNotFoundException {
        return ResponseEntity.ok().body(doctorService.getDoctorByCIN(cin));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<DoctorResponseDTO> createDoctor(@RequestBody DoctorRequestDTO doctorRequestDTO) {
        return ResponseEntity.ok().body(doctorService.createDoctor(doctorRequestDTO));
    }


    @PutMapping("/{cin}")
    public ResponseEntity<DoctorResponseDTO> updateDoctor(@RequestBody DoctorRequestDTO doctorRequestDTO,@PathVariable String cin) throws DoctorNotFoundException {
        return ResponseEntity.ok().body(doctorService.updateDoctor(doctorRequestDTO,cin));
    }

    @DeleteMapping("/{cin}")
    public ResponseEntity<Void> deleteDoctor(@PathVariable String cin) {
        return new ResponseEntity<>(HttpStatus.OK);
    }



}
