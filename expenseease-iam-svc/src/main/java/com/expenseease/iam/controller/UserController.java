package com.expenseease.iam.controller;

import com.expenseease.iam.dto.AuthenticationRequest;
import com.expenseease.iam.dto.AuthenticationResponse;
import com.expenseease.iam.dto.ExpenseEaseResponse;
import com.expenseease.iam.dto.RegistrationRequest;
import com.expenseease.iam.model.User;
import com.expenseease.iam.security.JwtTokenProvider;
import com.expenseease.iam.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/v1/api")
@CrossOrigin(origins = "*")
public class UserController {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserService userService;
    private final UserDetailsService userDetailsService;

    public UserController(AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider,
                          UserService userService, UserDetailsService userDetailsService) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.userService = userService;
        this.userDetailsService = userDetailsService;
    }

    @PostMapping("/register")
    public ResponseEntity<ExpenseEaseResponse> register(@RequestBody RegistrationRequest request) {
        userService.register(request);
        return ResponseEntity.ok().body(ExpenseEaseResponse.success("Registration Successful"));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<ExpenseEaseResponse> authenticate(@RequestBody AuthenticationRequest request) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getEmailId(), request.getPassword()));
            User user = (User) userDetailsService.loadUserByUsername(request.getEmailId());
            String token = jwtTokenProvider.generateToken(user);
            return ResponseEntity.ok(ExpenseEaseResponse.success("Authentication Successful", new AuthenticationResponse(token)));
        } catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ExpenseEaseResponse.failure("Invalid Credentials"));
        }
    }

    @GetMapping("/users")
    public ResponseEntity<ExpenseEaseResponse> findUsers(@RequestHeader("Authorization") String token) {
        return ResponseEntity.ok()
                .body(ExpenseEaseResponse.success("Users Fetched Successfully", Map.of("users", userService.findUsers())));
    }
}
