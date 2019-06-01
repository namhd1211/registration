package com.mitrais.registration.controller;

import com.mitrais.registration.entity.User;
import com.mitrais.registration.model.ApiCodeReference;
import com.mitrais.registration.model.ApiResponse;
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
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<ApiResponse> createUser(@Valid @RequestBody UserRequest requestUser) {
        User user = userService.createUser(requestUser);
        ApiResponse apiResponse = new ApiResponse(ApiCodeReference.SUCCESS,"insert thành công", user);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }
}
