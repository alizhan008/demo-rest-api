package com.example.demo.controllers;

import com.example.demo.dto.UsersDto;
import com.example.demo.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class UsersController {
    private final UserService userService;

    @GetMapping("/get/user/{id}")
    public Optional<UsersDto> getUserById(@PathVariable Long id){
        return Optional.ofNullable(this.userService.getUserById(id).orElse(null));
    }

    @PostMapping("/save/user")
    public ResponseEntity<UsersDto> saveUser(@RequestBody UsersDto user){
        if (user.getEmail() != null){
            this.userService.saveUser(user);
            userService.addRoles(user.getUserName(),"ADMIN");
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/update/user")
    public ResponseEntity<UsersDto> updateUser(@RequestBody UsersDto user){
        if (user.getUserName() != null){
            this.userService.updateUser(user);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

}
