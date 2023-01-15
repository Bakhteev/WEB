package com.example.lab4server.controller;
import org.springframework.web.bind.annotation.*;

@RestController
public class PingPongController {
    @GetMapping("/ping")
    public String pingPong() {
        return "pong";
    }
}
