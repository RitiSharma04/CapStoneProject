package com.natwest.exception.userregistrationexceptions;

public class InvalidMobileNumberFoundException extends RuntimeException{
    String message;
    public InvalidMobileNumberFoundException(String message){
        super(message);
        this.message=message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
