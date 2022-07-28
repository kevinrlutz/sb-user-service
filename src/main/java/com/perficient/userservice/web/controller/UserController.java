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
    public ResponseEntity<List<UserDto>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserDto> getUserById(@PathVariable("userId") String userId) {
        UserDto userDto = userService.getUserById(userId);
        return new ResponseEntity<>(userService.getUserById(userId), userDto == null ? HttpStatus.NOT_FOUND : HttpStatus.OK);
    }

    @GetMapping("/search/lastName/{lastName}")
    public ResponseEntity<List<UserDto>> getAllUsersByLastName(@PathVariable("lastName") String lastName) {
        return new ResponseEntity<>(userService.getAllUsersByLastName(lastName), HttpStatus.OK);
    }

    @GetMapping("/search/email/{email}")
    public ResponseEntity<List<UserDto>> getAllUsersByEmail(@PathVariable("email") String email) {
        return new ResponseEntity<>(userService.getAllUsersByEmail(email), HttpStatus.OK);
    }

    @GetMapping("/{userId}/appointments")
    public ResponseEntity<List<ApptDto>> getUserAppointments(@PathVariable("userId") String userId) {
        return new ResponseEntity<>(userService.getUserAppointments(userId), HttpStatus.OK);
    }

    @PostMapping("/") // Create a new user
    public ResponseEntity<UserDto> createUser(@RequestBody @Validated UserDto userDto) {
        return new ResponseEntity<>(userService.saveNewUser(userDto), HttpStatus.CREATED);
    }

    @PutMapping("/{userId}") // Update an existing user
    public ResponseEntity updateUserById(@PathVariable("userId") String userId, @RequestBody @Validated UserDto userDto) {
        return new ResponseEntity<>(userService.updateUser(userId, userDto), HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{userId}") // Delete an existing user
    public ResponseEntity deleteUser(@PathVariable("userId") String userId) {
        userService.deleteUser(userId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
