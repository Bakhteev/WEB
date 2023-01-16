package com.example.lab4server.controller;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@Tag(name="Ping pong")
public class PingPongController {
    @Operation(summary="Ping server")
    @GetMapping("/ping")
    public String pingPong() {
        return "pong";
    }
}
