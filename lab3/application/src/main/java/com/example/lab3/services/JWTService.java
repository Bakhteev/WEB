package com.example.lab3.services;

import com.example.lab3.dto.UserDto;
import com.example.lab3.entity.UserEntity;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;
import lombok.NoArgsConstructor;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Named(value ="jwtService")
@ApplicationScoped
@NoArgsConstructor
public class JWTService {
    private final SecretKey key = Keys.hmacShaKeyFor("RcMyNrPkhkXUi3hx0h1skhsdbhjbvhejvbjmdbvjhervmcv".getBytes(StandardCharsets.UTF_8));

    public boolean authenticate(String token) {
        Jws<Claims> jwsClaims = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token);
        String email = (String) jwsClaims.getBody().get("email");
        return email != null;
    }

    public Jws<Claims> getClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token);
    }

    public String createJwtToken(UserEntity user) {
        return Jwts.builder()
                .claim("email", user.getEmail())
                .claim("id", user.getId())
                .signWith(key)
                .setExpiration(new Date(new Date().getTime() + (1000 * 60 * 60 * 24)))
                .compact();
    }
    public String createJwtToken(UserDto user) {
        return Jwts.builder()
                .claim("email", user.getEmail())
                .signWith(key)
                .setExpiration(new Date(new Date().getTime() + (1000 * 60 * 60 * 24)))
                .compact();
    }
}
