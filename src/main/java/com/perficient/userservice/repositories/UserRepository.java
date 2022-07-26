package com.perficient.userservice.repositories;

import com.perficient.userservice.domain.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface UserRepository extends MongoRepository<User, String> {
    List<User> findByLastNameIgnoreCase(String lastName);

    List<User> findByEmailIgnoreCase(String email);
}
