package com.example.lab4server.controller;

import com.example.lab4server.dto.AuthDto;
import com.example.lab4server.dto.AuthenticatedUserResponseDto;
import com.example.lab4server.dto.ExceptionMessageDto;
import com.example.lab4server.entities.UserEntity;
import com.example.lab4server.mappers.UserMapper;
import com.example.lab4server.security.jwt.JwtUtils;
import com.example.lab4server.security.userDetails.UserDetailsImpl;
import com.example.lab4server.services.AuthService;
import com.example.lab4server.services.RefreshTokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private AuthService authService;
    private RefreshTokenService refreshTokenService;
    private JwtUtils jwtUtils;

    @Autowired
    public AuthController(AuthService authService, JwtUtils jwtUtils, RefreshTokenService refreshTokenService) {
        this.authService = authService;
        this.jwtUtils = jwtUtils;
        this.refreshTokenService = refreshTokenService;
    }

    @PostMapping("/registration")
    public ResponseEntity<AuthenticatedUserResponseDto> registration(@Valid @RequestBody AuthDto registrationDto) {
        UserEntity user = this.authService.registration(registrationDto);
        UserDetailsImpl userDetails = UserDetailsImpl.builder()
                .email(user.getEmail())
                .password(user.getPasswordHash())
                .build();
        String jwtAccessToken = jwtUtils.generateJwtAccessToken(userDetails);
        String jwtRefreshToken = refreshTokenService.createRefreshToken(user.getId()).getToken();
        return ResponseEntity.ok(
                new AuthenticatedUserResponseDto(jwtAccessToken, jwtRefreshToken, UserMapper.INSTANCE.toDTO(user))
        );
    }
}
