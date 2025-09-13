package org.app.consultationservice.exception;

public class ConsultationNotFoundException extends RuntimeException {
    public ConsultationNotFoundException(String message) {
        super(message);
    }
}
