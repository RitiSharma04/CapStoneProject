package com.natwest.exception.accountregistrationexceptions;

public class InvalidAccountNumberFoundException extends RuntimeException{
    private String msg;
    public InvalidAccountNumberFoundException(String message){
        super(message);
        this.msg=msg;
    }

    public String getMsg() {
        return msg;
    }
}
