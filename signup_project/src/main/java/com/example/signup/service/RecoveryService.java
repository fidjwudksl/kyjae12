package com.example.signup.service;

import com.example.signup.entity.User;
import com.example.signup.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RecoveryService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public ResponseEntity<String> findUsernameByDiscordId(String discordId) {
        Optional<User> user = userRepository.findByDiscordId(discordId);
        return user.map(value -> ResponseEntity.ok(value.getUsername()))
                   .orElse(ResponseEntity.badRequest().body("해당하는 사용자가 없습니다."));
    }

    public ResponseEntity<String> resetPassword(String discordId, String newPassword) {
        Optional<User> user = userRepository.findByDiscordId(discordId);
        if (user.isPresent()) {
            user.get().setPassword(encoder.encode(newPassword));
            userRepository.save(user.get());
            return ResponseEntity.ok("비밀번호가 재설정되었습니다.");
        }
        return ResponseEntity.badRequest().body("사용자를 찾을 수 없습니다.");
    }
}