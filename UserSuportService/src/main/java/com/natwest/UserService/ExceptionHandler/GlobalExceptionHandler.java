package com.natwest.UserService.ExceptionHandler;
import com.natwest.Authentication.Service.Utility.ErrorMessage;
import com.natwest.UserService.Exception.FilledRequired;
import com.natwest.UserService.Exception.ResourceNotFoundException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<String> handleConstraintViolationException(ConstraintViolationException ex) {
        // Check if the exception is related to password validation
        String errorMessage = "Validation failed";
        for (ConstraintViolation<?> violation : ex.getConstraintViolations()) {
            if ("password".equals(violation.getPropertyPath().toString())) {
                errorMessage = "Invalid password format";
                break;
            }if("email".equals(violation.getPropertyPath().toString())){
                errorMessage = "Invalid email format";
                break;
            }if ("phoneNumber".equals(violation.getPropertyPath().toString())) {
                errorMessage = "Invalid phone number format";
                break;
            }if ("username".equals(violation.getPropertyPath().toString())) {
                errorMessage = "Invalid username format";
                break;
            }if ("firstName".equals(violation.getPropertyPath().toString())) {
                errorMessage = "Invalid first name format";
                break;
            }if ("lastName".equals(violation.getPropertyPath().toString())) {
                errorMessage = "Invalid last name format";
                break;
            }

        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"message\": \"" + errorMessage + "\"}");
    }
    @ExceptionHandler(FilledRequired.class)
    public ResponseEntity<ErrorMessage> handleCredentialsNotCorrectException(FilledRequired ex) {
        ErrorMessage errorMessage = new ErrorMessage(new Date(), HttpStatus.BAD_REQUEST.value(), "Bad Request", ex.getMessage(), "");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorMessage> handleResourceNotFoundException(ResourceNotFoundException ex) {
        ErrorMessage errorMessage = new ErrorMessage(new Date(), HttpStatus.NOT_FOUND.value(), "Not Found", ex.getMessage(), "");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
    }
}
