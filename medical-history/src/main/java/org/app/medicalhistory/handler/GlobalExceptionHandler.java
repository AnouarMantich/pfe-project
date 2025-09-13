package org.app.medicalhistory.handler;


import org.app.medicalhistory.Exception.MedicalHistoryNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(MedicalHistoryNotFoundException.class)
    public ResponseEntity<Object> handleUserNotFoundException(MedicalHistoryNotFoundException ex){
        Map<String,Object> map = new HashMap<>();
        map.put("message", ex.getMessage());
        map.put("status", HttpStatus.NOT_FOUND);
        map.put("timestamp", LocalDateTime.now().toString());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(map);
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleValidationExceptions(
            MethodArgumentNotValidException ex, WebRequest request) {

        Map<String, String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .collect(Collectors.toMap(
                        fieldError -> fieldError.getField(),
                        fieldError -> fieldError.getDefaultMessage(),
                        (existing, replacement) -> existing // en cas de champ en doublon
                ));

        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }


}
