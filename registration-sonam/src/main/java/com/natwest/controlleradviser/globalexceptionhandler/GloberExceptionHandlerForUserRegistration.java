package com.natwest.controlleradviser.globalexceptionhandler;

import com.natwest.controlleradviser.errorinfo.ErrorInfoForUserRegistration;
import com.natwest.exception.userregistrationexceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
@RestControllerAdvice
public class GloberExceptionHandlerForUserRegistration {
    @ExceptionHandler(InvalidEmailFoundException.class)
    public ResponseEntity<ErrorInfoForUserRegistration> HandleExceptionForEmail(InvalidEmailFoundException e){
        ErrorInfoForUserRegistration errorInfo=new ErrorInfoForUserRegistration();
        errorInfo.setHttpStatus(HttpStatus.BAD_REQUEST);
        errorInfo.setErrorMessage(e.getMessage());
        errorInfo.setLocalDateTime(LocalDateTime.now());
        return new ResponseEntity<>(errorInfo, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(InvalidMobileNumberFoundException.class)
    public ResponseEntity<ErrorInfoForUserRegistration> HandleExceptionForBMobileNumber(InvalidMobileNumberFoundException e){
        ErrorInfoForUserRegistration errorInfo=new ErrorInfoForUserRegistration();
        errorInfo.setHttpStatus(HttpStatus.BAD_REQUEST);
        errorInfo.setErrorMessage(e.getMessage());
        errorInfo.setLocalDateTime(LocalDateTime.now());
        return new ResponseEntity<>(errorInfo, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(NameEmptyFoundException.class)
    public ResponseEntity<ErrorInfoForUserRegistration> HandleExceptionForName(NameEmptyFoundException e){
        ErrorInfoForUserRegistration errorInfo=new ErrorInfoForUserRegistration();
        errorInfo.setHttpStatus(HttpStatus.BAD_REQUEST);
        errorInfo.setErrorMessage(e.getMessage());
        errorInfo.setLocalDateTime(LocalDateTime.now());
        return new ResponseEntity<>(errorInfo, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(PasswordEmptyFoundException.class)
    public ResponseEntity<ErrorInfoForUserRegistration> HandleExceptionForPassword(PasswordEmptyFoundException e){
        ErrorInfoForUserRegistration errorInfo=new ErrorInfoForUserRegistration();
        errorInfo.setHttpStatus(HttpStatus.BAD_REQUEST);
        errorInfo.setErrorMessage(e.getMessage());
        errorInfo.setLocalDateTime(LocalDateTime.now());
        return new ResponseEntity<>(errorInfo, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(UserAlreadyExistexception.class)
    public ResponseEntity<ErrorInfoForUserRegistration> HandleUserAlreadyExist(UserAlreadyExistexception e){
        ErrorInfoForUserRegistration errorInfo=new ErrorInfoForUserRegistration();
        errorInfo.setHttpStatus(HttpStatus.BAD_REQUEST);
        errorInfo.setErrorMessage(e.getMessage());
        errorInfo.setLocalDateTime(LocalDateTime.now());
        return new ResponseEntity<>(errorInfo, HttpStatus.BAD_REQUEST);
    }
}
