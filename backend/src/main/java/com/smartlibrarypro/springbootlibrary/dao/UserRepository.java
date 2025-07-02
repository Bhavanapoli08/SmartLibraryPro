package com.smartlibrarypro.springbootlibrary.dao;

import com.smartlibrarypro.springbootlibrary.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    // 🔍 Find user by email address (used for login, registration, and security)
    Optional<User> findByEmail(String email);
}
