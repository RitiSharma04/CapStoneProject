package com.natwest.UserService.Service;

import com.natwest.UserService.Entity.User;
import com.natwest.UserService.Exception.FilledRequired;
import com.natwest.UserService.Exception.ResourceNotFoundException;
import com.natwest.UserService.Repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllUsers() {
        when(userRepository.findAll()).thenReturn(List.of(new User(), new User()));

        assertEquals(2, userService.getAllUsers().size());
    }

    @Test
    public void testGetUserById_ExistingUser() throws ResourceNotFoundException {
        User user = new User();
        when(userRepository.findById(1)).thenReturn(Optional.of(user));

        assertEquals(user, userService.getUserById(1));
    }

    @Test
    public void testGetUserById_NonExistingUser() {
        when(userRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> userService.getUserById(1));
    }

    @Test
    public void testCreateUser_ValidUser() throws FilledRequired {
        User user = new User();
        user.setFirstName("John");
        user.setLastName("Doe");
        user.setEmail("john@example.com");
        user.setPhoneNumber(BigInteger.valueOf(1234567890));
        user.setUsername("john.doe");
        user.setPassword("password");

        when(userRepository.save(any())).thenReturn(user);

        assertEquals(user, userService.createUser(user));
    }

    @Test
    public void testCreateUser_InvalidUser() {
        User user = new User(); // User without required fields

        assertThrows(FilledRequired.class, () -> userService.createUser(user));
    }

//    @Test
//    public void testUpdateUserEmail_ValidInput() throws ResourceNotFoundException {
//        User existingUser = new User();
//        existingUser.setUserId(1);
//        existingUser.setEmail("old@example.com");
//
//        when(userRepository.findById(1)).thenReturn(Optional.of(existingUser));
//
//        String newEmail = "new@example.com";
//        User updatedUser = userService.updateUserEmail(1, newEmail);
//
//        assertEquals(newEmail, updatedUser.getEmail());
//    }

    @Test
    public void testUpdateUserEmail_UserNotFound() {
        when(userRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> userService.updateUserEmail(1, "new@example.com"));
    }
}
