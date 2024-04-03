package com.natwest.exception.userregistrationexceptions;

public class InvalidEmailFoundException extends RuntimeException{
    String msg;
    public InvalidEmailFoundException(String message)
    {
        super(message);
        this.msg=message;
    }

    public String getMsg() {
        return msg;
    }
}
