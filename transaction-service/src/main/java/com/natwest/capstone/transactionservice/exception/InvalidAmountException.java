package com.natwest.capstone.transactionservice.exception;

public class InvalidAmountException extends Exception {
    private String invalidAmountMsg;
    public InvalidAmountException(String invalidAmountMessage) {
        super(invalidAmountMessage);
        this.invalidAmountMsg = invalidAmountMessage ;

    }

    public String getInvalidAmountMsg() {
        return invalidAmountMsg;
    }
}
