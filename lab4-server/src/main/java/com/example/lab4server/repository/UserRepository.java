package com.example.lab4server.repository;

import com.example.lab4server.entities.UserEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<UserEntity, String> {
    Optional<UserEntity> getUserByEmail(String email);
    Optional<UserEntity> getUserById(String userId);
}
