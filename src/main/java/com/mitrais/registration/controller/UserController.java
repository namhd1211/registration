package com.mitrais.registration.controller;

import com.mitrais.registration.entity.User;
import com.mitrais.registration.model.UserRequest;
import com.mitrais.registration.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<User> createUser(@Valid @RequestBody UserRequest requestUser) {
        User user = userService.createUser(requestUser);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}
