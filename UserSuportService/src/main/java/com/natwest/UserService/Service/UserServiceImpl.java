package com.natwest.UserService.Service;

import com.natwest.UserService.Entity.User;
import com.natwest.UserService.Exception.FilledRequired;
import com.natwest.UserService.Exception.ResourceNotFoundException;
import com.natwest.UserService.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(int userId) throws ResourceNotFoundException {
        return userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));
    }

    @Override
    public User createUser(User user) throws FilledRequired {
        validateUserCredentials(user);
        return userRepository.save(user);
    }

    @Override
    public User updateUser(int userId, User user) throws ResourceNotFoundException {
        return null;
    }

    @Override
    public User updateUserEmail(int userId, String newEmail) throws ResourceNotFoundException {
        User existingUser = getUserById(userId);
        existingUser.setEmail(newEmail);
        return userRepository.save(existingUser);
    }

    @Override
    public User updateUserUsername(int userId, String newUsername) throws ResourceNotFoundException {
        User existingUser = getUserById(userId);
        existingUser.setUsername(newUsername);
        return userRepository.save(existingUser);
    }

    @Override
    public User updateUserPhoneNumber(int userId, BigInteger newPhoneNumber) throws ResourceNotFoundException {
        User existingUser = getUserById(userId);
        existingUser.setPhoneNumber(newPhoneNumber);
        return userRepository.save(existingUser);
    }

    @Override
    public User updateUserPassword(int userId, String newPassword) throws ResourceNotFoundException {
        User existingUser = getUserById(userId);
        existingUser.setPassword(newPassword);
        return userRepository.save(existingUser);
    }

    @Override
    public User updateUserFirstName(int userId, String newFirstName) throws ResourceNotFoundException {
        User existingUser = getUserById(userId);
        existingUser.setFirstName(newFirstName);
        return userRepository.save(existingUser);
    }

    @Override
    public User updateUserLastName(int userId, String newLastName) throws ResourceNotFoundException {
        User existingUser = getUserById(userId);
        existingUser.setLastName(newLastName);
        return userRepository.save(existingUser);
    }

    @Override
    public void deleteUser(int userId) throws ResourceNotFoundException {
        if (userRepository.existsById(userId)) {
            userRepository.deleteById(userId);
        } else {
            throw new ResourceNotFoundException("User not found with id: " + userId);
        }
    }

    private void validateUserCredentials(User user) throws FilledRequired {
        if (user.getFirstName() == null || user.getFirstName().isEmpty()) {
            throw new FilledRequired("First name is required");
        }
        if (user.getLastName() == null || user.getLastName().isEmpty()) {
            throw new FilledRequired("Last name is required");
        }
        if (user.getEmail() == null || user.getEmail().isEmpty()) {
            throw new FilledRequired("Email is required");
        }
        if (user.getPhoneNumber() == null || user.getPhoneNumber().equals(BigInteger.ZERO)) {
            throw new FilledRequired("Phone number is required");
        }

        if (user.getUsername() == null || user.getUsername().isEmpty()) {
            throw new FilledRequired("Username is required");
        }
        if (user.getPassword() == null || user.getPassword().isEmpty()) {
            throw new FilledRequired("Password is required");
        }
    }
}


