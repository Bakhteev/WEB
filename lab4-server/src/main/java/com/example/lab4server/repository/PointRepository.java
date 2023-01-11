package com.example.lab4server.repository;

import com.example.lab4server.entities.PointEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PointRepository extends MongoRepository<PointEntity, String>{
}
