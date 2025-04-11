package com.example.signup.repository;

import java.util.Optional;

import com.example.signup.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByDiscordId(String discordId);
    boolean existsByNickname(String nickname);
    boolean existsByUsername(String username);

    Optional<User> findByUsername(String username);
}