package com.example.lab2.services;

import com.example.lab2.models.User;
import com.example.lab2.state.UserState;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;


public class JWTService {

    private final SecretKey key = Keys.hmacShaKeyFor("RcMyNrPkhkXUi3hx0h1skhsdbhjbvhejvbjmdbvjhervmcv".getBytes(StandardCharsets.UTF_8));
    UserState userState;

    public JWTService(UserState userState) {
        this.userState = userState;
    }

    public boolean authenticate(String token) {
        Jws<Claims> jwsClaims = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token);
        String email = (String) jwsClaims.getBody().get("email");
        return userState.getUserByEmail(email) != null;
    }

    public Jws<Claims> getClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token);
    }

    public String createJwtToken(User user) {
        return Jwts.builder()
                .claim("email", user.getEmail())
                .claim("userId", user.getId())
                .signWith(key)
                .setExpiration(new Date(new Date().getTime() + (1000 * 60 * 60 * 24)))
                .compact();
    }
}
