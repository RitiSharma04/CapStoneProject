package com.natwest.service;

import com.natwest.dto.UserDto;
import com.natwest.exception.userregistrationexceptions.InvalidEmailFoundException;
import com.natwest.exception.userregistrationexceptions.InvalidMobileNumberFoundException;
import com.natwest.exception.userregistrationexceptions.NameEmptyFoundException;
import com.natwest.exception.userregistrationexceptions.PasswordEmptyFoundException;
import com.natwest.model.User;

import java.util.Optional;

public interface UserService {
    public User RegisterUser(User user) throws InvalidEmailFoundException, InvalidMobileNumberFoundException, NameEmptyFoundException, PasswordEmptyFoundException;
    public UserDto getUserById(int id);

}
