package com.natwest.capstone.transactionservice.controlleradvice;

import com.natwest.capstone.transactionservice.exception.AccountDoesNotExistException;
import com.natwest.capstone.transactionservice.exception.HistoryNotFoundException;
import com.natwest.capstone.transactionservice.exception.InsufficientBalanceException;
import com.natwest.capstone.transactionservice.exception.InvalidAmountException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionController {

    @ExceptionHandler(InsufficientBalanceException.class)
    public ResponseEntity<ErrorInfo> insufficientBalExceptionHandler(InsufficientBalanceException exception){

        ErrorInfo info = new ErrorInfo();
        info.setErrorMsg(exception.getMsg());
        info.setStatus(HttpStatus.BAD_REQUEST);
        info.setTime(LocalDateTime.now());

        return new ResponseEntity<>(info,HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(InvalidAmountException.class)
    public ResponseEntity<ErrorInfo> invalidAmountExceptionHandler(InvalidAmountException exception){

        ErrorInfo info = new ErrorInfo();
        info.setErrorMsg(exception.getInvalidAmountMsg());
        info.setStatus(HttpStatus.BAD_REQUEST);
        info.setTime(LocalDateTime.now());

        return new ResponseEntity<>(info,HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(AccountDoesNotExistException.class)
    public ResponseEntity<ErrorInfo> accountCouldNotFound(AccountDoesNotExistException exception)
    {
        ErrorInfo info = new ErrorInfo();
        info.setErrorMsg(exception.getMessage());
        info.setStatus(HttpStatus.BAD_REQUEST);
        info.setTime(LocalDateTime.now());

        return new ResponseEntity<>(info,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HistoryNotFoundException.class)
    public ResponseEntity<ErrorInfo> historyCouldNotFound(HistoryNotFoundException exception)
    {
        ErrorInfo info = new ErrorInfo();
        info.setErrorMsg(exception.getMessage());
        info.setStatus(HttpStatus.BAD_REQUEST);
        info.setTime(LocalDateTime.now());

        return new ResponseEntity<>(info,HttpStatus.BAD_REQUEST);
    }
}
