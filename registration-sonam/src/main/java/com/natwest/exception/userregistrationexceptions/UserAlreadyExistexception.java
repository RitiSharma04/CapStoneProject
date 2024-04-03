package com.natwest.exception.userregistrationexceptions;

public class UserAlreadyExistexception extends RuntimeException {
    String msg;
    public UserAlreadyExistexception(String message) {
        super(message);
        this.msg=message;
    }
    public String getMsg() {
        return msg;
    }
}
