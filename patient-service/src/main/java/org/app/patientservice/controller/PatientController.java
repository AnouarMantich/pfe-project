package org.app.patientservice.controller;


import lombok.RequiredArgsConstructor;
import org.app.patientservice.dto.PatientRequestDTO;
import org.app.patientservice.dto.PatientResponseDTO;
import org.app.patientservice.exception.PatientNotFoundException;
import org.app.patientservice.service.PatientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/patients")
@RequiredArgsConstructor
public class PatientController {

    private final PatientService patientService;


    @GetMapping
    public ResponseEntity<List<PatientResponseDTO>> getAllPatientsByName(
            @RequestParam(defaultValue = "") String name,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "0") int size,
            @RequestParam(defaultValue = "id")String sortBy,
            @RequestParam(defaultValue = "asc") String orderBy


    ) {
        return ResponseEntity.ok(patientService.getAllPatientsByName(name,page,size,sortBy,orderBy));
    }

    @GetMapping("/{cin}")
    public ResponseEntity<PatientResponseDTO> getPatientByUserCIN(@PathVariable String cin) throws PatientNotFoundException {
        return ResponseEntity.ok(patientService.getPatientByCIN(cin));
    }


    @PostMapping
    public ResponseEntity<PatientResponseDTO> createPatient(
            @RequestBody PatientRequestDTO patientRequestDTO) {
        return new ResponseEntity<>(patientService.createPatient(patientRequestDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{cin}")
    public ResponseEntity<PatientResponseDTO> updatePatient(@PathVariable String cin ,@RequestBody PatientRequestDTO patientRequestDTO) throws PatientNotFoundException {
        return ResponseEntity.ok(patientService.updatePatient(cin,patientRequestDTO));
    }

    @DeleteMapping("/{cin}")
    public ResponseEntity<Void> deletePatientById(@PathVariable String cin) {
        patientService.deletePatient(cin);
        return ResponseEntity.ok().build();
    }



}
