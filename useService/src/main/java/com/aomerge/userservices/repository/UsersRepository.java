package com.aomerge.userservices.repository;

import com.aomerge.userservices.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends MongoRepository<User, String> {
    User findByEmail(String email);
    User findByEmailAndPassword(String email, String password);
}
