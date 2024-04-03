package com.natwest.controller;

import com.natwest.dto.UserDto;
import com.natwest.model.User;
import com.natwest.service.UserService;
import com.natwest.utility.UserAppConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/user")
    public ResponseEntity<String> registerUser(@RequestBody User user) {
        userService.RegisterUser(user);
        return new ResponseEntity<>(UserAppConstants.REGISTRATION_SUCCESFULL,HttpStatus.OK);
    }
    @GetMapping("/user/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable int id) {
        UserDto userDto = userService.getUserById(id);
        return ResponseEntity.ok(userDto);
    }

}


