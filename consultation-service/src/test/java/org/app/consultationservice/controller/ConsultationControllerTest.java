package org.app.consultationservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.app.consultationservice.dto.ConsultationRequestDTO;
import org.app.consultationservice.dto.ConsultationResponseDTO;
import org.app.consultationservice.service.ConsultationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ConsultationController.class)
class ConsultationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ConsultationService consultationService;

    @Autowired
    private ObjectMapper objectMapper;

    private ConsultationRequestDTO testRequestDTO;
    private ConsultationResponseDTO testResponseDTO;
    private UUID testId;

    @BeforeEach
    void setUp() {
        testId = UUID.randomUUID();
        
        testRequestDTO = new ConsultationRequestDTO();
        testRequestDTO.setPatientId("patient123");
        testRequestDTO.setDoctorId("doctor456");
        testRequestDTO.setDate(LocalDateTime.now());
        testRequestDTO.setSymptoms("Fever and cough");
        testRequestDTO.setDiagnosis("Common cold");
        testRequestDTO.setNotes("Patient should rest and drink fluids");

        testResponseDTO = new ConsultationResponseDTO();
        testResponseDTO.setId(testId);
        testResponseDTO.setPatientId("patient123");
        testResponseDTO.setDoctorId("doctor456");
        testResponseDTO.setDate(LocalDateTime.now());
        testResponseDTO.setSymptoms("Fever and cough");
        testResponseDTO.setDiagnosis("Common cold");
        testResponseDTO.setNotes("Patient should rest and drink fluids");
    }

    @Test
    void createConsultation_ShouldReturnCreatedConsultation() throws Exception {
        // Given
        when(consultationService.createConsultation(any(ConsultationRequestDTO.class)))
                .thenReturn(testResponseDTO);

        // When & Then
        mockMvc.perform(post("/api/v1/consultations")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(testRequestDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(testId.toString()))
                .andExpect(jsonPath("$.patientId").value("patient123"))
                .andExpect(jsonPath("$.doctorId").value("doctor456"))
                .andExpect(jsonPath("$.symptoms").value("Fever and cough"));
    }

    @Test
    void getConsultationById_ShouldReturnConsultation() throws Exception {
        // Given
        when(consultationService.getConsultationById(testId))
                .thenReturn(testResponseDTO);

        // When & Then
        mockMvc.perform(get("/api/v1/consultations/{id}", testId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(testId.toString()))
                .andExpect(jsonPath("$.patientId").value("patient123"))
                .andExpect(jsonPath("$.doctorId").value("doctor456"));
    }

    @Test
    void getConsultationsByPatientId_ShouldReturnListOfConsultations() throws Exception {
        // Given
        String patientId = "patient123";
        when(consultationService.getConsultationsByPatientId(patientId))
                .thenReturn(List.of(testResponseDTO));

        // When & Then
        mockMvc.perform(get("/api/v1/consultations/patient/{patientId}", patientId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].id").value(testId.toString()))
                .andExpect(jsonPath("$[0].patientId").value("patient123"));
    }

    @Test
    void getConsultationsByDoctorId_ShouldReturnPageOfConsultations() throws Exception {
        // Given
        String doctorId = "doctor456";
        Page<ConsultationResponseDTO> page = new PageImpl<>(List.of(testResponseDTO));
        when(consultationService.getConsultationsByDoctorId(eq(doctorId), any(Pageable.class)))
                .thenReturn(page);

        // When & Then
        mockMvc.perform(get("/api/v1/consultations/doctor/{doctorId}", doctorId)
                        .param("page", "0")
                        .param("size", "10"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content").isArray())
                .andExpect(jsonPath("$.content[0].id").value(testId.toString()))
                .andExpect(jsonPath("$.content[0].doctorId").value("doctor456"));
    }

    @Test
    void updateConsultation_ShouldReturnUpdatedConsultation() throws Exception {
        // Given
        when(consultationService.updateConsultation(eq(testId), any(ConsultationRequestDTO.class)))
                .thenReturn(testResponseDTO);

        // When & Then
        mockMvc.perform(put("/api/v1/consultations/{id}", testId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(testRequestDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(testId.toString()))
                .andExpect(jsonPath("$.patientId").value("patient123"));
    }

    @Test
    void deleteConsultation_ShouldReturnNoContent() throws Exception {
        // Given
        // No return value for void method

        // When & Then
        mockMvc.perform(delete("/api/v1/consultations/{id}", testId))
                .andExpect(status().isNoContent());
    }

    @Test
    void createConsultation_WithInvalidData_ShouldReturnBadRequest() throws Exception {
        // Given
        ConsultationRequestDTO invalidDTO = new ConsultationRequestDTO();
        invalidDTO.setPatientId(""); // Invalid: empty patient ID
        invalidDTO.setDoctorId(""); // Invalid: empty doctor ID

        // When & Then
        mockMvc.perform(post("/api/v1/consultations")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(invalidDTO)))
                .andExpect(status().isBadRequest());
    }
}
