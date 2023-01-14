package com.example.lab4server.repository;

import com.example.lab4server.entities.PointEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface PointRepository extends MongoRepository<PointEntity, String>{
    Page<PointEntity> findAllPointsByUserId(String userId, Pageable pageable);
}
