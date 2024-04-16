package com.expenseease.iam.service;

import com.expenseease.iam.dto.RegistrationRequest;
import com.expenseease.iam.dto.UserDTO;
import com.expenseease.iam.model.User;
import com.expenseease.iam.repository.UserRepository;
import com.expenseease.iam.util.Utility;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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

    public List<UserDTO> findUsers() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(user -> Utility.mapObject(user, UserDTO.class))
                .collect(Collectors.toList());
    }

}
