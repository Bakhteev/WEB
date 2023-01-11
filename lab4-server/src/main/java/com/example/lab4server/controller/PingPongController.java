package com.example.lab4server.controller;


import com.example.lab4server.dto.UserDto;
import com.example.lab4server.entities.UserEntity;
import com.example.lab4server.mappers.UserMapper;
import com.example.lab4server.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;
import java.util.List;

@RestController
public class PingPongController {

    final UserRepository userRepository;

    @Autowired
    public PingPongController(UserRepository UserRepository) {
        this.userRepository = UserRepository;
    }

    @GetMapping("/ping")
    public String pingPong() {
        return "pong";
    }

    @PostMapping("/create")
    public UserDto create(@RequestBody UserDto userDto) {
        return UserMapper.INSTANCE.toDTO(userRepository.save(new UserEntity(userDto)));
    }
    @GetMapping("/xui")
    public String xui() {
        return "xui";
    }

    @GetMapping("/all")
    public List<UserEntity> getAll() {
        System.out.println("dkfhjdfdf");
        return userRepository.findAll();
    }
}
