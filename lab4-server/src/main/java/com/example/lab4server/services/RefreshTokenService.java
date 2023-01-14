package com.example.lab4server.services;

import com.example.lab4server.entities.RefreshTokenEntity;
import com.example.lab4server.entities.UserEntity;
import com.example.lab4server.httpExcetions.ForbiddenException;
import com.example.lab4server.httpExcetions.NotFountException;
import com.example.lab4server.repository.RefreshTokenRepository;
import com.example.lab4server.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Service
public class RefreshTokenService {

    @Value("${bakhteev.app.jwtRefreshExpirationMs}")
    private Long refreshTokenDurationMs;

    private final RefreshTokenRepository refreshTokenRepository;
    private final UserRepository userRepository;

    @Autowired
    public RefreshTokenService(RefreshTokenRepository refreshTokenRepository, UserRepository userRepository) {
        this.refreshTokenRepository = refreshTokenRepository;
        this.userRepository = userRepository;
    }

    public RefreshTokenEntity findByToken(String token) {
        return refreshTokenRepository.findByToken(token);
    }

    public RefreshTokenEntity createRefreshToken(String userId) {
        RefreshTokenEntity refreshToken = new RefreshTokenEntity();
        Optional<UserEntity> userEntityOptional = userRepository.findById(userId);
        if (userEntityOptional.isPresent()) {
            refreshToken.setUserId(userEntityOptional.get().getId());
            refreshToken.setExpiryDate(Instant.now().plusMillis(refreshTokenDurationMs));
            refreshToken.setToken(UUID.randomUUID().toString());
            refreshToken = refreshTokenRepository.save(refreshToken);
            return refreshToken;
        }
        return null;
    }

    public RefreshTokenEntity verifyExpiration(RefreshTokenEntity token) {
        if (token.getExpiryDate().compareTo(Instant.now()) < 0) {
            refreshTokenRepository.delete(token);
            throw new ForbiddenException("Refresh token was expired. Please make a new log in request");
        }
        return token;
    }

    public int deleteByUserId(String userId) {
        Optional<UserEntity> userEntityOptional = userRepository.findById(userId);
        if (userEntityOptional.isPresent()) {
            return refreshTokenRepository.deleteByUserId(userEntityOptional.get().getId());
        }
        throw new NotFountException();
    }
}
