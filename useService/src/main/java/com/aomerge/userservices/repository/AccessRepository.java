package com.aomerge.userservices.repository;

import com.aomerge.userservices.models.Access;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccessRepository extends MongoRepository<Access, String> {
    Optional<Access> findByUserId(String userId);
}
