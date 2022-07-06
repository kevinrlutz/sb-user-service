package com.perficient.userservice.repositories;

import com.perficient.userservice.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    Page<User> findAllByLastName(String lastName, Pageable pageable);

    Page<User> findAllByLastNameAndUserId(String lastName, UUID userId, Pageable pageable);

    Optional<User> findByUserId(UUID userId);
}
