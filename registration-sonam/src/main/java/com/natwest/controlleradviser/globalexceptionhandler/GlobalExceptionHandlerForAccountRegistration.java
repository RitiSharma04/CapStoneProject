package com.natwest.controlleradviser.globalexceptionhandler;

import com.natwest.controlleradviser.errorinfo.ErrorInfoForAccountRegistration;
import com.natwest.exception.accountregistrationexceptions.AccountAlreadyExistException;
import com.natwest.exception.accountregistrationexceptions.InsufficientBalanceFoundException;
import com.natwest.exception.accountregistrationexceptions.InvalidAccountNumberFoundException;
import com.natwest.exception.accountregistrationexceptions.InvalidIfcsCodeFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
@RestControllerAdvice
public class GlobalExceptionHandlerForAccountRegistration {
    @ExceptionHandler(AccountAlreadyExistException.class)
    public ResponseEntity<ErrorInfoForAccountRegistration> HandleExceptionForAccountExistence(AccountAlreadyExistException a){
        ErrorInfoForAccountRegistration errorInfo=new ErrorInfoForAccountRegistration();
        errorInfo.setHttpStatus(HttpStatus.BAD_REQUEST);
        errorInfo.setErrorMessage(a.getMessage());
        errorInfo.setLocalDateTime(LocalDateTime.now());
        return new ResponseEntity<>(errorInfo, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(InsufficientBalanceFoundException.class)
    public ResponseEntity<ErrorInfoForAccountRegistration> HandleExceptionForInsufficientBalance(InsufficientBalanceFoundException a){
        ErrorInfoForAccountRegistration errorInfo=new ErrorInfoForAccountRegistration();
        errorInfo.setHttpStatus(HttpStatus.BAD_REQUEST);
        errorInfo.setErrorMessage(a.getMessage());
        errorInfo.setLocalDateTime(LocalDateTime.now());
        return new ResponseEntity<>(errorInfo, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(InvalidAccountNumberFoundException.class)
    public ResponseEntity<ErrorInfoForAccountRegistration> HandleExceptionForInvalidAccountNumber(InvalidAccountNumberFoundException a){
        ErrorInfoForAccountRegistration errorInfo=new ErrorInfoForAccountRegistration();
        errorInfo.setHttpStatus(HttpStatus.BAD_REQUEST);
        errorInfo.setErrorMessage(a.getMessage());
        errorInfo.setLocalDateTime(LocalDateTime.now());
        return new ResponseEntity<>(errorInfo, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(InvalidIfcsCodeFoundException.class)
    public ResponseEntity<ErrorInfoForAccountRegistration> HandleExceptionForInvalidIfcsCode(InvalidIfcsCodeFoundException a){
        ErrorInfoForAccountRegistration errorInfo=new ErrorInfoForAccountRegistration();
        errorInfo.setHttpStatus(HttpStatus.BAD_REQUEST);
        errorInfo.setErrorMessage(a.getMessage());
        errorInfo.setLocalDateTime(LocalDateTime.now());
        return new ResponseEntity<>(errorInfo, HttpStatus.BAD_REQUEST);
    }


}
