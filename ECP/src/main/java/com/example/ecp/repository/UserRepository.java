package com.example.ecp.repository;

import com.example.ecp.entity.Users;
import org.apache.catalina.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


public interface UserRepository extends JpaRepository<Users, UUID> {
    @Query("FROM Users where username = ?1")
    Optional<Users> findByUsername(String username);
    Optional<Users> findByEmail(String email);
    Users deleteByUsername(String username);
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);

}
