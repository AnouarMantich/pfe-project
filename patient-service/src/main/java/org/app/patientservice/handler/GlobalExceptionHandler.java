package org.app.patientservice.handler;

import org.app.patientservice.exception.PatientNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {



    @ExceptionHandler(PatientNotFoundException.class)
    public ResponseEntity<Map<String,Object>> handleException(PatientNotFoundException ex){
        Map<String,Object> map = new HashMap<>();
        map.put("message", ex.getMessage());
        map.put("timestamp", System.currentTimeMillis());
        map.put("status", HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
    }

}
