package com.natwest.UserService.Service;

import com.natwest.UserService.Entity.User;
import com.natwest.UserService.Exception.FilledRequired;
import com.natwest.UserService.Exception.ResourceNotFoundException;

import java.math.BigInteger;
import java.util.List;

public interface UserService {

    List<User> getAllUsers();
    User getUserById(int userId) throws ResourceNotFoundException;
    User createUser(User user) throws FilledRequired;
    User updateUser(int userId, User user) throws ResourceNotFoundException;
    User updateUserEmail(int userId, String newEmail) throws ResourceNotFoundException;
    User updateUserUsername(int userId, String newUsername) throws ResourceNotFoundException;
    User updateUserPhoneNumber(int userId, BigInteger newPhoneNumber) throws ResourceNotFoundException;
    User updateUserPassword(int userId, String newPassword) throws ResourceNotFoundException;
    User updateUserFirstName(int userId, String newFirstName) throws ResourceNotFoundException;
    User updateUserLastName(int userId, String newLastName) throws ResourceNotFoundException;
    void deleteUser(int userId) throws ResourceNotFoundException;

}
