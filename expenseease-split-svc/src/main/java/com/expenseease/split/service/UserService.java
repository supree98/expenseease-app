package com.expenseease.split.service;

import com.expenseease.split.model.User;
import com.expenseease.split.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User findUser(String email) {
        return userRepository.findByEmail(email);
    }


}
