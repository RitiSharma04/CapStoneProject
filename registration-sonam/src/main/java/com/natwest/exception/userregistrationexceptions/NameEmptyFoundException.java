package com.natwest.exception.userregistrationexceptions;

public class NameEmptyFoundException extends RuntimeException{
    String message;
    public NameEmptyFoundException(String message){
        super(message);
        this.message=message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
