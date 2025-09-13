package org.app.medicalhistory.controller;

import lombok.RequiredArgsConstructor;
import org.app.medicalhistory.dto.MedicalHistoryRequestDTO;
import org.app.medicalhistory.dto.MedicalHistoryResponseDTO;
import org.app.medicalhistory.service.MedicalHistoryService;
import org.app.medicalhistory.validation.OnCreate;
import org.app.medicalhistory.validation.OnUpdate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;


@RestController
@RequestMapping("/api/v1/medical-histories")
@RequiredArgsConstructor
public class MedicalHistoryController {

    private final MedicalHistoryService medicalHistoryService;

    @PostMapping
    public ResponseEntity<MedicalHistoryResponseDTO> createMedicalHistory(
            @Validated(OnCreate.class) @RequestBody MedicalHistoryRequestDTO dto
    ){
        return new ResponseEntity<>(medicalHistoryService.createMedicalHistory(dto), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MedicalHistoryResponseDTO> getMedicalHistoryById(
            @PathVariable UUID id
    ){
        return ResponseEntity.ok(medicalHistoryService.getMedicalHistoryById(id));
    }

    @GetMapping("/patient/{patientId}")
    public ResponseEntity<MedicalHistoryResponseDTO> getMedicalHistoryByPatientId(
            @PathVariable String patientId
    ){
        return ResponseEntity.ok(medicalHistoryService.getMedicalHistoryByPatientId(patientId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MedicalHistoryResponseDTO> updateMedicalHistory(
            @PathVariable UUID id,
            @Validated(OnUpdate.class) @RequestBody MedicalHistoryRequestDTO dto
    ){
        return new ResponseEntity<>(medicalHistoryService.updateMedicalHistory(id,dto),HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMedicalHistory(
            @PathVariable UUID id
    ){

        medicalHistoryService.deleteMedicalHistory(id);

        return ResponseEntity.ok().build();
    }








}
