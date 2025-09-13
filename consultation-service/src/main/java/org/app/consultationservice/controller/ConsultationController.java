package org.app.consultationservice.controller;

import lombok.RequiredArgsConstructor;
import org.app.consultationservice.dto.ConsultationRequestDTO;
import org.app.consultationservice.dto.ConsultationResponseDTO;
import org.app.consultationservice.service.ConsultationService;
import org.app.consultationservice.validation.Create;
import org.app.consultationservice.validation.Update;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/consultations")
@RequiredArgsConstructor
public class ConsultationController {


    private final ConsultationService consultationService;

    @PostMapping
    public ResponseEntity<ConsultationResponseDTO> createConsultation(
            @RequestBody @Validated(Create.class) ConsultationRequestDTO dto) {

        ConsultationResponseDTO created = consultationService.createConsultation(dto);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ConsultationResponseDTO> getConsultationById(@PathVariable UUID id) {
        ConsultationResponseDTO dto = consultationService.getConsultationById(id);
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/patient/{patientId}")
    public ResponseEntity<List<ConsultationResponseDTO>> getConsultationsByPatientId(@PathVariable String patientId) {
        List<ConsultationResponseDTO> consultations = consultationService.getConsultationsByPatientId(patientId);
        return ResponseEntity.ok(consultations);
    }

    @GetMapping("/doctor/{doctorId}")
    public ResponseEntity<Page<ConsultationResponseDTO>> getConsultationsByDoctorId(
            @PathVariable String doctorId,
            @PageableDefault(
                    page = 0,
                    size = 10,
                    sort = "date",
                    direction = Sort.Direction.DESC
            ) Pageable pageable) {

        Page<ConsultationResponseDTO> page = consultationService.getConsultationsByDoctorId(doctorId, pageable);
        return ResponseEntity.ok(page);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ConsultationResponseDTO> updateConsultation(
            @PathVariable UUID id,
            @RequestBody @Validated(Update.class) ConsultationRequestDTO dto) {

        ConsultationResponseDTO updated = consultationService.updateConsultation(id, dto);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteConsultation(@PathVariable UUID id) {
        consultationService.deleteConsultation(id);
        return ResponseEntity.noContent().build();
    }


}
