package com.example.lab4server.controller;

import com.example.lab4server.dto.request.AuthDto;
import com.example.lab4server.dto.request.RefreshTokenRequestDto;
import com.example.lab4server.dto.response.AuthenticatedUserResponseDto;
import com.example.lab4server.dto.response.RefreshResponseDto;
import com.example.lab4server.entities.RefreshTokenEntity;
import com.example.lab4server.entities.UserEntity;
import com.example.lab4server.httpExcetions.BadRequestException;
import com.example.lab4server.httpExcetions.UnauthorizedException;
import com.example.lab4server.mappers.UserMapper;
import com.example.lab4server.security.jwt.JwtUtils;
import com.example.lab4server.security.userDetails.UserDetailsImpl;
import com.example.lab4server.services.AuthService;
import com.example.lab4server.services.RefreshTokenService;
import com.example.lab4server.services.UserDetailsServiceImpl;
import com.example.lab4server.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@Tag(name = "Authentication")
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

    @Operation(summary = "Registration path")
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

    @Operation(summary = "Log in path")
    @PostMapping("/login")
    public ResponseEntity<AuthenticatedUserResponseDto> login(@Valid @RequestBody AuthDto loginDto) {
        UserEntity user = this.authService.login(loginDto);
        this.userDetailsServiceImpl.AuthenticateUserInContext(user.getEmail());
        String jwtAccessToken = jwtUtils.generateJwtAccessToken(user);
        RefreshTokenEntity refreshTokenEntity = refreshTokenService.findByUserId(user.getId());
        String jwtRefreshToken;
        if (refreshTokenEntity != null) {
            refreshTokenService.deleteByUserId(user.getId());
        }
        jwtRefreshToken = refreshTokenService.createRefreshToken(user.getId()).getToken();
        return ResponseEntity.ok(
                new AuthenticatedUserResponseDto(jwtAccessToken, jwtRefreshToken, UserMapper.INSTANCE.toDTO(user))
        );
    }

    @Operation(summary = "Refresh authentication")
    @PostMapping("/refresh")
    public ResponseEntity<RefreshResponseDto> refreshAuth(@Valid @RequestBody RefreshTokenRequestDto request) {
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
        throw new BadCredentialsException("Invalid refresh token");
    }

    @Operation(security = {@SecurityRequirement(name = "bearer-key")}, summary = "Log out path")
    @PostMapping("/logout")
    public ResponseEntity<?> logoutUser() {
        try {
            UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            String userId = userDetails.getId();
            refreshTokenService.deleteByUserId(userId);
            return ResponseEntity.ok("Log out successful!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new UnauthorizedException();
        }
    }
}
