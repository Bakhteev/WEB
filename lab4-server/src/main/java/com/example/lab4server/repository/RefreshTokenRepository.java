package com.example.lab4server.repository;

import com.example.lab4server.entities.RefreshTokenEntity;
import com.example.lab4server.entities.UserEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RefreshTokenRepository extends MongoRepository<RefreshTokenEntity, String> {
    RefreshTokenEntity findByToken(String token);

    int deleteByUserId(String userId);
}
