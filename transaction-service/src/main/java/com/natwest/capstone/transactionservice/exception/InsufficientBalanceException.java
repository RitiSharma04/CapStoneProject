package com.natwest.capstone.transactionservice.exception;

public class InsufficientBalanceException extends Exception {
    private String insufficientBalanceMessage ;
    public InsufficientBalanceException(String insufficientBalanceMessage) {
        super(insufficientBalanceMessage);
        this.insufficientBalanceMessage = insufficientBalanceMessage;
    }
    public String getMsg(){
        return insufficientBalanceMessage ;
    }
}
