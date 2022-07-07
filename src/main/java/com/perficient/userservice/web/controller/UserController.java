package com.perficient.userservice.web.controller;

import com.perficient.userservice.model.UserDto;
import com.perficient.userservice.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RequestMapping("/api/v1/users")
@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/{userId}")
    public ResponseEntity<UserDto> getUserByUuid(@PathVariable("userId") UUID userId) {
        return new ResponseEntity<>(userService.getUserById(userId), HttpStatus.OK);
    }

    @PostMapping // Create a new user
    public ResponseEntity<UserDto> createUser(@RequestBody @Validated UserDto userDto) {
        return new ResponseEntity<>(userService.saveNewUser(userDto), HttpStatus.CREATED);
    }

    @PutMapping("/{userId}") // Update an existing user
    public ResponseEntity updateUserById(@PathVariable("userId") UUID userId, @RequestBody @Validated UserDto userDto) {
        return new ResponseEntity<>(userService.updateUser(userId, userDto), HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{userId}") // Delete an existing user
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable("userId") UUID userId) {
        userService.deleteUser(userId);
    }
}
