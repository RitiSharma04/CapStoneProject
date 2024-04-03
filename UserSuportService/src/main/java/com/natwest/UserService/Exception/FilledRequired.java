package com.natwest.UserService.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class FilledRequired extends Exception {
    public FilledRequired(String messeg){
        super(messeg);
    }
}
