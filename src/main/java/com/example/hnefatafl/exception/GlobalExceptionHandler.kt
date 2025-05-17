package com.example.hnefatafl.exception

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class GlobalExceptionHandler {
    @ExceptionHandler(ObjectNotFoundException::class)
    fun handleGameNotFound(ex: ObjectNotFoundException): ResponseEntity<String?> {
        return ResponseEntity.status(404).body(ex.message)
    }

    @ExceptionHandler(ObjectAlreadyExistsException::class)
    fun handleObjectAlreadyExists(ex: ObjectAlreadyExistsException): ResponseEntity<String?> {
        return ResponseEntity.status(404).body(ex.message)
    }

    @ExceptionHandler(IndexOutOfBoundsException::class)
    fun handleIndexOutOfBound(ex: IndexOutOfBoundsException): ResponseEntity<String> {
        var identify = ""
        for (stack in ex.stackTrace) {
            if (stack.classLoaderName === "app") {
                identify = stack.fileName + "[" + stack.lineNumber + "] - "
                break
            }
        }
        return ResponseEntity.status(404).body(identify + ex.message)
    }
}