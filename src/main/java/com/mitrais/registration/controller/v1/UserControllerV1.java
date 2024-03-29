package com.mitrais.registration.controller.v1;

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
public class UserControllerV1 {

    private final UserService userService;

    @Autowired
    public UserControllerV1(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<ApiResponse> createUser(@Valid @RequestBody UserRequest requestUser) throws Exception {
        User user = userService.createUser(requestUser);
        ApiResponse apiResponse = new ApiResponse(ApiCodeReference.SUCCESS, user);
        return new ResponseEntity<>(apiResponse, HttpStatus.CREATED);
    }
}
