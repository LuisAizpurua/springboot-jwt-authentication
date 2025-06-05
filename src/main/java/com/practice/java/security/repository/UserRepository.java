package com.practice.java.security.repository;

import com.practice.java.security.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Integer> {

   Optional<User> findByUsername(String username);

    Optional<User> findByPassword(String password);

    Optional<Boolean> existsByUsername(String username);
}
