package com.perficient.userservice.web.controller;

import com.perficient.userservice.web.model.ApptDto;
import com.perficient.userservice.web.model.UserDto;
import com.perficient.userservice.services.UserService;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/v1/users")
@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<UserDto> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<UserDto> getUserById(@PathVariable("userId") String userId) {
        return new ResponseEntity<>(userService.getUserById(userId), HttpStatus.OK);
    }

    @GetMapping("/search/{lastName}")
    @ResponseStatus(HttpStatus.OK)
    public List<UserDto> getAllUsersByLastName(@PathVariable("lastName") String lastName) {
        return userService.getAllUsersByLastName(lastName);
    }

    @GetMapping("/{userId}/appointments")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<ApptDto>> getUserAppointments(@PathVariable("userId") String userId) {
        return new ResponseEntity<>(userService.getUserAppointments(userId), HttpStatus.OK);
    }

    @PostMapping("/") // Create a new user
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<UserDto> createUser(@RequestBody @Validated UserDto userDto) {
        return new ResponseEntity<>(userService.saveNewUser(userDto), HttpStatus.CREATED);
    }

    @PutMapping("/{userId}") // Update an existing user
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity updateUserById(@PathVariable("userId") String userId, @RequestBody @Validated UserDto userDto) {
        return new ResponseEntity<>(userService.updateUser(userId, userDto), HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{userId}") // Delete an existing user
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable("userId") String userId) {
        userService.deleteUser(userId);
    }
}
