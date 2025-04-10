package com.example.hnefatafl.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<String> handleGameNotFound(ObjectNotFoundException ex) {
        return ResponseEntity.status(404).body(ex.getMessage());
    }

    @ExceptionHandler(ObjectAlreadyExistsException.class)
    public ResponseEntity<String> handleObjectAlreadyExists(ObjectAlreadyExistsException ex) {
        return ResponseEntity.status(404).body(ex.getMessage());
    }

    @ExceptionHandler(IndexOutOfBoundsException.class)
    public ResponseEntity<String> handleIndexOutOfBound(IndexOutOfBoundsException ex) {
        String identify = "";
        for (StackTraceElement stack:ex.getStackTrace()) {
            if( stack.getClassLoaderName()=="app") {
                identify = stack.getFileName() + "[" + stack.getLineNumber() + "] - ";
                break;
            }
        }

        return ResponseEntity.status(404).body(identify + ex.getMessage());
    }
}
