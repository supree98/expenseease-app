package com.expenseease.iam.service;

import com.expenseease.iam.dto.RegistrationRequest;
import com.expenseease.iam.dto.UserDTO;
import com.expenseease.iam.model.User;
import com.expenseease.iam.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void register(RegistrationRequest request) {
        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        userRepository.save(user);
    }

    public List<UserDTO> findUsers() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(u -> new UserDTO(u.getId(), u.getName(), u.getEmail()))
                .collect(Collectors.toList());
    }

}
