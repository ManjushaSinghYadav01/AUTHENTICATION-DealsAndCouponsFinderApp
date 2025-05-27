package com.UserService.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<String> handleUserAlreadyExists(UserAlreadyExistsException ex) {
    	 return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage()); // 409
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<String> handleUserNotFound(UserNotFoundException ex) {
    	return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage()); // 404 // Updated
    }
    
    @ExceptionHandler(InvalidInputException.class)
    public ResponseEntity<String> handleInvalidInput(InvalidInputException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage()); // 400
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGenericException(Exception ex) {
    	 return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                 .body("An error occurred: " + ex.getMessage()); // Generic 500
    }
}
