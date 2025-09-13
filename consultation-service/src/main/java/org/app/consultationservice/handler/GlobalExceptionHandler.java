package org.app.consultationservice.handler;

import org.app.consultationservice.exception.BaseGlobalExceptionHandler;
import org.app.consultationservice.exception.ConsultationNotFoundException;
import org.app.consultationservice.exception.StandardErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends BaseGlobalExceptionHandler {



    // Gestion des exceptions personnalisées (ex: Consultation non trouvée)
    @ExceptionHandler(ConsultationNotFoundException.class)
    public ResponseEntity<StandardErrorResponse> handleConsultationNotFound(ConsultationNotFoundException ex) {
        return buildErrorResponse(HttpStatus.NOT_FOUND, "Consultation Not Found", ex.getMessage());
    }

}
