package com.natwest.exception.userregistrationexceptions;

public class PasswordEmptyFoundException extends RuntimeException {
    String message;
    public PasswordEmptyFoundException(String message){
        super(message);
        this.message=message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
