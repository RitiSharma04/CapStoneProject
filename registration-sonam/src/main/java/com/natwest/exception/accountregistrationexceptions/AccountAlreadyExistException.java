package com.natwest.exception.accountregistrationexceptions;

public class AccountAlreadyExistException extends RuntimeException{
    private String msg;
    public AccountAlreadyExistException(String message){
        super(message);
        this.msg=msg;
    }

    public String getMsg() {
        return msg;
    }
}
