package com.example.lab4server.repository;

import com.example.lab4server.entities.UserEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
public interface UserRepository extends MongoRepository<UserEntity, String> {
}
