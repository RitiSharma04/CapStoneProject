package com.natwest.UserService.Controller;

import com.natwest.UserService.Entity.User;
import com.natwest.UserService.Exception.FilledRequired;
import com.natwest.UserService.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.List;

@RestController
@RequestMapping("/user")
public class CustomerSuportController {
    //http://localhost:8099/users/register
    @Autowired
    UserService userService;
//
//    @PostMapping("/register")
//    public ResponseEntity<User> createUser(@RequestBody User user) throws FilledRequired {
//        User createdUser = userService.createUser(user);
//        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
//    }
    //http://localhost:8097/user/all
    @GetMapping("/all")
    public ResponseEntity<List<User>> allUsers() {
        List<User> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }
    //http://localhost:8097/user/{userId}

    @GetMapping("/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable int userId) {
        User user = userService.getUserById(userId);
        return ResponseEntity.ok(user);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<User> updateUser(@PathVariable int userId, @RequestBody User user) {
        User updatedUser = userService.updateUser(userId, user);
        return ResponseEntity.ok(updatedUser);
    }

    @PatchMapping("/{userId}/email")
    public ResponseEntity<User> updateUserEmail(@PathVariable int userId, @RequestParam String newEmail) {
        User updatedUser = userService.updateUserEmail(userId, newEmail);
        return ResponseEntity.ok(updatedUser);
    }

    @PatchMapping("/{userId}/username")
    public ResponseEntity<User> updateUserUsername(@PathVariable int userId, @RequestParam String newUsername) {
        User updatedUser = userService.updateUserUsername(userId, newUsername);
        return ResponseEntity.ok(updatedUser);
    }

    @PatchMapping("/{userId}/password")
    public ResponseEntity<User> updateUserPassword(@PathVariable int userId, @RequestParam String newPassword) {
        User updatedUser = userService.updateUserPassword(userId, newPassword);
        return ResponseEntity.ok(updatedUser);
    }

    @PatchMapping("/{userId}/phone")
    public ResponseEntity<User> updateUserPhoneNumber(@PathVariable int userId, @RequestParam BigInteger newPhoneNumber) {
        User updatedUser = userService.updateUserPhoneNumber(userId, newPhoneNumber);
        return ResponseEntity.ok(updatedUser);
    }

    @PatchMapping("/{userId}/first-name")
    public ResponseEntity<User> updateUserFirstName(@PathVariable int userId, @RequestParam String newFirstName) {
        User updatedUser = userService.updateUserFirstName(userId, newFirstName);
        return ResponseEntity.ok(updatedUser);
    }

    @PatchMapping("/{userId}/last-name")
    public ResponseEntity<User> updateUserLastName(@PathVariable int userId, @RequestParam String newLastName) {
        User updatedUser = userService.updateUserLastName(userId, newLastName);
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable int userId) {
        userService.deleteUser(userId);
        return ResponseEntity.noContent().build();
    }
}
