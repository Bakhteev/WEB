package com.example.lab4.controller;

import com.example.lab4.dto.UserDto;
import com.example.lab4.entities.UserEntity;
import com.example.lab4.repository.UserRepository;
import com.mongodb.spi.dns.DnsWithResponseCodeException;
import jdk.jfr.ContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PingPongController {

    final UserRepository userRepository;

    @Autowired
    public PingPongController(UserRepository UserRepository) {
        this.userRepository = UserRepository;
    }

    @GetMapping("/ping")
    public String pingPong(){
        return "pong";
    }

    @PostMapping("/create")
    public ResponseEntity<UserEntity> create(@RequestBody UserDto userDto){
       return ResponseEntity.ok(userRepository.save(new UserEntity(userDto)));
    }

    @GetMapping("/all")
    public ResponseEntity<List<UserEntity>> getAll(){
        return ResponseEntity.ok(userRepository.findAll());
    }
}
