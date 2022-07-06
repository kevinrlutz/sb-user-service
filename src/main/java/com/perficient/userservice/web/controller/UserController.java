package com.perficient.userservice.web.controller;

import com.perficient.userservice.model.UserDto;
import com.perficient.userservice.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RequestMapping("/api/v1/users")
@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/{userId}")
    public ResponseEntity<UserDto> getUser(@PathVariable("userId") UUID userId) {
        return new ResponseEntity<>(userService.getUserById(userId), HttpStatus.OK);
    }

    @PostMapping // Create a new user
    public ResponseEntity<UserDto> createUser(UserDto userDto) {
        UserDto savedUser = userService.saveUser(userDto);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", "/api/v1/users/" + savedUser.getUserId().toString());

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{userId}") // Update an existing user
    public ResponseEntity<UserDto> updateUserById(@PathVariable("userId") UUID userId, UserDto userDto) {
        userService.updateUser(userId, userDto);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{userId}") // Delete an existing user
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable("userId") UUID userId) {
        userService.deleteUser(userId);
    }
}
