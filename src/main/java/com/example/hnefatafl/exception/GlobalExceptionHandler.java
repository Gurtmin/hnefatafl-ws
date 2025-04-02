package com.example.hnefatafl.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<String> handleGameNotFound(ObjectNotFoundException ex) {
        return ResponseEntity.status(404).body(ex.getMessage());
    }
}
