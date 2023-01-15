package com.example.lab4server.repository;

import com.example.lab4server.entities.HitEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface HitRepository extends MongoRepository<HitEntity, String>{
    Page<HitEntity> findAllPointsByUserId(String userId, Pageable pageable);
}
