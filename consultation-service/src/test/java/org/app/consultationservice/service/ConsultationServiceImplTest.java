package org.app.consultationservice.service;

import org.app.consultationservice.dto.ConsultationRequestDTO;
import org.app.consultationservice.dto.ConsultationResponseDTO;
import org.app.consultationservice.entity.Consultation;
import org.app.consultationservice.exception.ConsultationNotFoundException;
import org.app.consultationservice.mapper.ConsultationMapper;
import org.app.consultationservice.repository.ConsultationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ConsultationServiceImplTest {

    @Mock
    private ConsultationRepository consultationRepository;

    @InjectMocks
    private ConsultationServiceImpl consultationService;

    private Consultation testConsultation;
    private ConsultationRequestDTO testRequestDTO;
    private ConsultationResponseDTO testResponseDTO;
    private UUID testId;

    @BeforeEach
    void setUp() {
        testId = UUID.randomUUID();
        testConsultation = new Consultation();
        testConsultation.setId(testId);
        testConsultation.setPatientId("patient123");
        testConsultation.setDoctorId("doctor456");
        testConsultation.setDate(LocalDateTime.now());
        testConsultation.setSymptoms("Fever and cough");
        testConsultation.setDiagnosis("Common cold");
        testConsultation.setNotes("Patient should rest and drink fluids");

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
    void createConsultation_ShouldReturnCreatedConsultation() {
        // Given
        when(consultationRepository.save(any(Consultation.class))).thenReturn(testConsultation);

        try (MockedStatic<ConsultationMapper> mapperMock = mockStatic(ConsultationMapper.class)) {
            mapperMock.when(() -> ConsultationMapper.toEntity(testRequestDTO)).thenReturn(testConsultation);
            mapperMock.when(() -> ConsultationMapper.toDto(testConsultation)).thenReturn(testResponseDTO);

            // When
            ConsultationResponseDTO result = consultationService.createConsultation(testRequestDTO);

            // Then
            assertNotNull(result);
            assertEquals(testId, result.getId());
            assertEquals("patient123", result.getPatientId());
            assertEquals("doctor456", result.getDoctorId());
            verify(consultationRepository).save(any(Consultation.class));
        }
    }

    @Test
    void getConsultationById_WhenConsultationExists_ShouldReturnConsultation() {
        // Given
        when(consultationRepository.findById(testId)).thenReturn(Optional.of(testConsultation));

        try (MockedStatic<ConsultationMapper> mapperMock = mockStatic(ConsultationMapper.class)) {
            mapperMock.when(() -> ConsultationMapper.toDto(testConsultation)).thenReturn(testResponseDTO);

            // When
            ConsultationResponseDTO result = consultationService.getConsultationById(testId);

            // Then
            assertNotNull(result);
            assertEquals(testId, result.getId());
            verify(consultationRepository).findById(testId);
        }
    }

    @Test
    void getConsultationById_WhenConsultationNotFound_ShouldThrowException() {
        // Given
        when(consultationRepository.findById(testId)).thenReturn(Optional.empty());

        // When & Then
        ConsultationNotFoundException exception = assertThrows(
                ConsultationNotFoundException.class,
                () -> consultationService.getConsultationById(testId)
        );

        assertEquals("Consultation not found for ID: " + testId, exception.getMessage());
        verify(consultationRepository).findById(testId);
    }

    @Test
    void getConsultationsByPatientId_ShouldReturnListOfConsultations() {
        // Given
        String patientId = "patient123";
        List<Consultation> consultations = List.of(testConsultation);
        when(consultationRepository.findByPatientId(patientId)).thenReturn(consultations);

        try (MockedStatic<ConsultationMapper> mapperMock = mockStatic(ConsultationMapper.class)) {
            mapperMock.when(() -> ConsultationMapper.toDto(testConsultation)).thenReturn(testResponseDTO);

            // When
            List<ConsultationResponseDTO> result = consultationService.getConsultationsByPatientId(patientId);

            // Then
            assertNotNull(result);
            assertEquals(1, result.size());
            assertEquals(testId, result.get(0).getId());
            verify(consultationRepository).findByPatientId(patientId);
        }
    }

    @Test
    void getConsultationsByDoctorId_ShouldReturnPageOfConsultations() {
        // Given
        String doctorId = "doctor456";
        Pageable pageable = PageRequest.of(0, 10, Sort.by("date").descending());
        Page<Consultation> consultationPage = new PageImpl<>(List.of(testConsultation), pageable, 1);
        when(consultationRepository.findByDoctorId(doctorId, pageable)).thenReturn(consultationPage);

        try (MockedStatic<ConsultationMapper> mapperMock = mockStatic(ConsultationMapper.class)) {
            mapperMock.when(() -> ConsultationMapper.toDto(testConsultation)).thenReturn(testResponseDTO);

            // When
            Page<ConsultationResponseDTO> result = consultationService.getConsultationsByDoctorId(doctorId, pageable);

            // Then
            assertNotNull(result);
            assertEquals(1, result.getContent().size());
            assertEquals(testId, result.getContent().get(0).getId());
            verify(consultationRepository).findByDoctorId(doctorId, pageable);
        }
    }

    @Test
    void updateConsultation_WhenConsultationExists_ShouldReturnUpdatedConsultation() {
        // Given
        when(consultationRepository.findById(testId)).thenReturn(Optional.of(testConsultation));
        when(consultationRepository.save(any(Consultation.class))).thenReturn(testConsultation);

        try (MockedStatic<ConsultationMapper> mapperMock = mockStatic(ConsultationMapper.class)) {
            mapperMock.when(() -> ConsultationMapper.toDto(testConsultation)).thenReturn(testResponseDTO);

            // When
            ConsultationResponseDTO result = consultationService.updateConsultation(testId, testRequestDTO);

            // Then
            assertNotNull(result);
            assertEquals(testId, result.getId());
            verify(consultationRepository).findById(testId);
            verify(consultationRepository).save(any(Consultation.class));
        }
    }

    @Test
    void updateConsultation_WhenConsultationNotFound_ShouldThrowException() {
        // Given
        when(consultationRepository.findById(testId)).thenReturn(Optional.empty());

        // When & Then
        ConsultationNotFoundException exception = assertThrows(
                ConsultationNotFoundException.class,
                () -> consultationService.updateConsultation(testId, testRequestDTO)
        );

        assertEquals("Consultation not found for ID: " + testId, exception.getMessage());
        verify(consultationRepository).findById(testId);
        verify(consultationRepository, never()).save(any(Consultation.class));
    }

    @Test
    void deleteConsultation_WhenConsultationExists_ShouldDeleteSuccessfully() {
        // Given
        when(consultationRepository.existsById(testId)).thenReturn(true);

        // When
        consultationService.deleteConsultation(testId);

        // Then
        verify(consultationRepository).existsById(testId);
        verify(consultationRepository).deleteById(testId);
    }

    @Test
    void deleteConsultation_WhenConsultationNotFound_ShouldThrowException() {
        // Given
        when(consultationRepository.existsById(testId)).thenReturn(false);

        // When & Then
        ConsultationNotFoundException exception = assertThrows(
                ConsultationNotFoundException.class,
                () -> consultationService.deleteConsultation(testId)
        );

        assertEquals("Consultation not found for ID: " + testId, exception.getMessage());
        verify(consultationRepository).existsById(testId);
        verify(consultationRepository, never()).deleteById(any(UUID.class));
    }
}
