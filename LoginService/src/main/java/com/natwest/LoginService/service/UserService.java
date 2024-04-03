package com.natwest.LoginService.service;


import com.natwest.LoginService.Exception.FilledRequired;
import com.natwest.LoginService.Exception.ResourceNotFoundException;
import com.natwest.LoginService.entity.User;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;
@Service
public interface UserService {

    List<User> getAllUsers();
    User getUserById(int userId) throws ResourceNotFoundException;
    User createUser(User user) throws FilledRequired;
    void deleteUser(int userId) throws ResourceNotFoundException;

}
