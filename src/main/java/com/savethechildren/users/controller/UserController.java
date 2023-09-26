package com.savethechildren.users.controller;

import com.savethechildren.users.dto.UserDto;
import com.savethechildren.users.exception.RecordExistsException;
import com.savethechildren.users.service.UserService;
import jakarta.validation.Valid;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public ResponseEntity<List<UserDto>> getUserList() {
        return ResponseEntity.ok(userService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable UUID id) {
        Optional<UserDto> user = userService.findById(id);
        if (user.isPresent()) {
            return ResponseEntity.ok(user.get());
        }
        return ResponseEntity.notFound().build();

    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto user) throws RecordExistsException {

        UserDto createdUser = userService.save(user);

        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }

    @PutMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<String> updateUser(@PathVariable UUID id, @Valid @RequestBody UserDto user) throws RecordExistsException {

        Optional<UserDto> updatedUserDto = userService.update(user);

        if(updatedUserDto.isPresent()) {
           return ResponseEntity.status(HttpStatus.CREATED).body("Resource updated successfully");
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable UUID id) {
        ResponseEntity<Void> responseEntity = ResponseEntity.notFound().build();
        if(userService.existsById(id)) {
            userService.deleteById(id);
            responseEntity =  ResponseEntity.noContent().build();
        }
        return responseEntity;
    }
}
