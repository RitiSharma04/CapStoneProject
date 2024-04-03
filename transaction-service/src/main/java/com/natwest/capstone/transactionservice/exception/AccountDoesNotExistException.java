package com.natwest.capstone.transactionservice.exception;

public class AccountDoesNotExistException extends Exception {
    public AccountDoesNotExistException(String message) {
        super(message);
    }
}
