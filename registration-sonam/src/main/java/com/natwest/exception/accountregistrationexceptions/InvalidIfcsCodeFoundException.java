package com.natwest.exception.accountregistrationexceptions;

public class InvalidIfcsCodeFoundException extends RuntimeException{
    private String msg;
    public InvalidIfcsCodeFoundException(String message){
        super(message);
        this.msg=msg;
    }
    public String getMsg() {
        return msg;
    }
}
