package com.expenseease.group.service;

import com.expenseease.group.model.User;
import com.expenseease.group.repository.UserRepository;
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
