package com.expenseease.iam.controller;

import com.expenseease.iam.dto.RegistrationRequest;
import com.expenseease.iam.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/api")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/registration")
    public ResponseEntity<String> register(@RequestBody RegistrationRequest request) {
        userService.register(request);
        return ResponseEntity.ok().body("Registration Completed Successfully!");
    }
}
