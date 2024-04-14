package com.expenseease.iam.service;

import com.expenseease.iam.dto.RegistrationRequest;
import com.expenseease.iam.model.User;
import com.expenseease.iam.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void register(RegistrationRequest request) {
        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        userRepository.save(user);
    }

    public User findUser(String email) {
        return userRepository.findByEmail(email);
    }

}
