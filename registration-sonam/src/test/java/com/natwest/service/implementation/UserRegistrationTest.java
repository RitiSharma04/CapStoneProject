package com.natwest.service.implementation;

import com.natwest.dto.UserDto;
import com.natwest.exception.userregistrationexceptions.*;
import com.natwest.model.User;
import com.natwest.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
public class UserRegistrationTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserRegistration userService;

    @Test
    public void testAddUser_ValidUser_Success() throws InvalidEmailFoundException, InvalidMobileNumberFoundException, NameEmptyFoundException, PasswordEmptyFoundException {
        // Given
        User user = new User();
        user.setEmailId("sonam@gmail.com");
        user.setMobileNumber("1234567890");
        user.setName("Sonam kumari");
        user.setPassword("password");

        // Mocking userRepository.save() to return the user
        when(userRepository.save(any(User.class))).thenReturn(user);

        // When
        User savedUser = userService.RegisterUser(user);

        // Then
        assertNotNull(savedUser);
        assertEquals(user, savedUser);
    }

    @Test
    public void testAddUser_InvalidEmail_ExceptionThrown() {
        // Given
        User user = new User();
        user.setEmailId("invalidEmail");
        user.setMobileNumber("1234567890");
        user.setName("Sonam kumari");
        user.setPassword("password");

        // Then
        assertThrows(InvalidEmailFoundException.class, () -> userService.RegisterUser(user));
    }

    @Test
    public void testAddUser_InvalidMobileNumber_ExceptionThrown() {
        // Given
        User user = new User();
        user.setEmailId("sonam@gmail.com");
        user.setMobileNumber("123"); // Invalid mobile number (less than 10 digits)
        user.setName("Sonam kumari");
        user.setPassword("password");

        // Then
        assertThrows(InvalidMobileNumberFoundException.class, () -> userService.RegisterUser(user));
    }

    @Test
    public void testAddUser_NameEmpty_ExceptionThrown() {
        // Given
        User user = new User();
        user.setEmailId("sonam@gmail.com");
        user.setMobileNumber("1234567890");
        user.setName(""); // Empty name
        user.setPassword("password");

        // Then
        assertThrows(NameEmptyFoundException.class, () -> userService.RegisterUser(user));
    }

    @Test
    public void testAddUser_PasswordEmpty_ExceptionThrown() {
        // Given
        User user = new User();
        user.setEmailId("sonam@gmail.com");
        user.setMobileNumber("1234567890");
        user.setName("Sonam kumari");
        user.setPassword(""); // Empty password

        // Then
        assertThrows(PasswordEmptyFoundException.class, () -> userService.RegisterUser(user));
    }
    @Test
    void testRegisterUser_Positive() throws UserAlreadyExistexception, InvalidEmailFoundException, InvalidMobileNumberFoundException, NameEmptyFoundException, PasswordEmptyFoundException {
        // Create a sample User
        User user = new User();
        user.setId(1);
        user.setName("John Doe");
        user.setEmailId("johndoe@gmail.com");
        user.setMobileNumber("1234567890");
        user.setPassword("password123");

        // Mock the behavior of userRepository.existsById to return false (user doesn't exist)
        when(userRepository.existsById(1)).thenReturn(false);
        // Mock the behavior of userRepository.save to return the same user
        when(userRepository.save(user)).thenReturn(user);

        // Call the method under test
        User registeredUser = userService.RegisterUser(user);

        // Verify that the returned User is the same as the input User
        assertEquals(user, registeredUser);
    }
    @Test
    void testRegisterUser_EmailInvalid() {
        // Create a sample User with invalid email
        User user = new User();
        user.setEmailId("invalidemail");

        // Call the method under test and expect InvalidEmailFoundException
        assertThrows(InvalidEmailFoundException.class, () -> userService.RegisterUser(user));
    }

    @Test
    void testRegisterUser_MobileNumberInvalid() {
        // Create a sample User with invalid mobile number
        User user = new User();
        user.setId(1);
        user.setName("John Doe");
        user.setEmailId("johndoe@gmail.com");
        user.setMobileNumber("12345");
        user.setPassword("password123"); // shorter than 10 digits

        // Call the method under test and expect InvalidMobileNumberFoundException
        assertThrows(InvalidMobileNumberFoundException.class, () -> userService.RegisterUser(user));
    }

    @Test
    void testRegisterUser_NameEmpty() {
        // Create a sample User with empty name
        User user = new User();
        user.setId(1);
        user.setName("");
        user.setEmailId("johndoe@gmail.com");
        user.setMobileNumber("1234590876");
        user.setPassword("password123");

        // Call the method under test and expect NameEmptyFoundException
        assertThrows(NameEmptyFoundException.class, () -> userService.RegisterUser(user));
    }

    @Test
    void testRegisterUser_PasswordEmpty() {
        // Create a sample User with empty password
        User user = new User();
        user.setId(1);
        user.setName("John Doe");
        user.setEmailId("johndoe@gmail.com");
        user.setMobileNumber("1234567584");
        user.setPassword("");

        // Call the method under test and expect PasswordEmptyFoundException
        assertThrows(PasswordEmptyFoundException.class, () -> userService.RegisterUser(user));
    }

    @Test
    void testRegisterUser_UserAlreadyExists() {
        // Create a sample User
        User user = new User();
        user.setId(1);

        // Mock the behavior of userRepository.existsById to return true (user already exists)
        when(userRepository.existsById(1)).thenReturn(true);

        // Call the method under test and expect UserAlreadyExistexception
        assertThrows(UserAlreadyExistexception.class, () -> userService.RegisterUser(user));
    }

    @Test
    void testGetUserById() {
        // Create a sample User
        User user = new User();
        user.setId(1);
        user.setName("John Doe");
        user.setEmailId("johndoe@gmail.com");
        user.setMobileNumber("1234567890");
        user.setPassword("password123");

        // Mock the behavior of userRepository.findById to return an Optional containing this user
        when(userRepository.findById(1)).thenReturn(Optional.of(user));

        // Call the method under test
        UserDto userDto = userService.getUserById(1);

        // Verify that the ModelMapper is called and mapping is performed correctly
        assertEquals(user.getId(), userDto.getId());
        assertEquals(user.getName(), userDto.getName());
        assertEquals(user.getEmailId(), userDto.getEmailId());
        assertEquals(user.getMobileNumber(), userDto.getMobileNumber());
        // Add assertions for other fields if needed
    }




}
