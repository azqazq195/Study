package com.example.jwt.user.domain.repository;

import com.example.jwt.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByEmail(String email);
}
