package com.example.lab4server.services;

import com.example.lab4server.entities.UserEntity;
import com.example.lab4server.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserEntity getUserById(String userId) {
        return userRepository.getUserById(userId).orElse(null);
    }
}
