package com.natwest.exception.accountregistrationexceptions;

public class InsufficientBalanceFoundException extends RuntimeException {
    private String msg;
    public InsufficientBalanceFoundException(String message){
        super(message);
        this.msg=msg;
    }
    public String getMsg() {
        return msg;
    }
}
