package com.example.ecp.repository;

import com.example.ecp.entity.Users;
import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<Users, Integer> {
    Optional<Users> findByUsername(String username);
    Boolean existsByUsername(String username);
    boolean existsByEmail(String email);
}
