package com.myapp.countapi.controller;

import com.myapp.countapi.models.CountResponse;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<CountResponse> handleEntityNotFoundException(EntityNotFoundException ex) {
        CountResponse response = new CountResponse();
        response.setError(ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<CountResponse> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex) {
        CountResponse countResponse = new CountResponse();
        countResponse.setError("Parameter '" + ex.getPropertyName() + "' should be provided as an '" + ex.getRequiredType() + "' type.");
        return new ResponseEntity<>(countResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<CountResponse> handleDataIntegrityViolationException(DataIntegrityViolationException ex) {
        String errorMessage = ex.getRootCause().getMessage();
        CountResponse countResponse = new CountResponse();
        if (errorMessage.contains("Unique index")) {
            countResponse.setError("Username is already registered");
        } else {
            countResponse.setError("An error occurred while key generation");
        }
        return new ResponseEntity<>(countResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<CountResponse> handleGenericException(Exception ex) {
        CountResponse response = new CountResponse();
        response.setError("Internal server error");
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
