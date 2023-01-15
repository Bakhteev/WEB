package com.example.lab4server.controller;

import com.example.lab4server.dto.request.AuthDto;
import com.example.lab4server.dto.request.RefreshTokenRequestDto;
import com.example.lab4server.dto.response.AuthenticatedUserResponseDto;
import com.example.lab4server.dto.response.RefreshResponseDto;
import com.example.lab4server.entities.RefreshTokenEntity;
import com.example.lab4server.entities.UserEntity;
import com.example.lab4server.httpExcetions.BadRequestException;
import com.example.lab4server.mappers.UserMapper;
import com.example.lab4server.security.jwt.JwtUtils;
import com.example.lab4server.security.userDetails.UserDetailsImpl;
import com.example.lab4server.services.AuthService;
import com.example.lab4server.services.RefreshTokenService;
import com.example.lab4server.services.UserDetailsServiceImpl;
import com.example.lab4server.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;
    private final AuthService authService;
    private final RefreshTokenService refreshTokenService;
    private final UserDetailsServiceImpl userDetailsServiceImpl;
    private final JwtUtils jwtUtils;

    @Autowired
    public AuthController(AuthService authService,
                          JwtUtils jwtUtils,
                          RefreshTokenService refreshTokenService,
                          UserService userService,
                          UserDetailsServiceImpl userDetailsServiceImpl) {
        this.authService = authService;
        this.jwtUtils = jwtUtils;
        this.refreshTokenService = refreshTokenService;
        this.userService = userService;
        this.userDetailsServiceImpl = userDetailsServiceImpl;
    }

    @PostMapping("/registration")
    public ResponseEntity<AuthenticatedUserResponseDto> registration(@Valid @RequestBody AuthDto registrationDto) {
        UserEntity user = this.authService.registration(registrationDto);
        this.userDetailsServiceImpl.AuthenticateUserInContext(user.getEmail());
        String jwtAccessToken = jwtUtils.generateJwtAccessToken(user);
        String jwtRefreshToken = refreshTokenService.createRefreshToken(user.getId()).getToken();
        return ResponseEntity.ok(
                new AuthenticatedUserResponseDto(jwtAccessToken, jwtRefreshToken, UserMapper.INSTANCE.toDTO(user))
        );
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticatedUserResponseDto> login(@Valid @RequestBody AuthDto loginDto) {
        UserEntity user = this.authService.login(loginDto);
        this.userDetailsServiceImpl.AuthenticateUserInContext(user.getEmail());
        String jwtAccessToken = jwtUtils.generateJwtAccessToken(user);
        RefreshTokenEntity refreshTokenEntity = refreshTokenService.findByUserId(user.getId());
        String jwtRefreshToken;
        if (refreshTokenEntity != null) {
            jwtRefreshToken = refreshTokenEntity.getToken();
        } else {
            jwtRefreshToken = refreshTokenService.createRefreshToken(user.getId()).getToken();
        }
        return ResponseEntity.ok(
                new AuthenticatedUserResponseDto(jwtAccessToken, jwtRefreshToken, UserMapper.INSTANCE.toDTO(user))
        );
    }

    @PostMapping("/refresh")
    public ResponseEntity<?> refreshAuth(@Valid @RequestBody RefreshTokenRequestDto request) {
        String requestRefreshToken = request.getRefreshToken();
        RefreshTokenEntity refreshTokenEntity = refreshTokenService.findByToken(requestRefreshToken);
        if (refreshTokenEntity != null) {
            refreshTokenService.verifyExpiration(refreshTokenEntity);
            UserEntity user = userService.getUserById(refreshTokenEntity.getUserId());
            if (user != null) {
                this.userDetailsServiceImpl.AuthenticateUserInContext(user.getEmail());
                String accessToken = jwtUtils.generateJwtAccessToken(user);
                return ResponseEntity.ok(new RefreshResponseDto(accessToken, requestRefreshToken));
            }
        }
        throw new BadRequestException("Invalid refresh token");
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logoutUser() {
        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String userId = userDetails.getId();
        refreshTokenService.deleteByUserId(userId);
        return ResponseEntity.ok("Log out successful!");
    }
}
