package com.example.signup.service;

import com.example.signup.entity.VerificationCode;
import com.example.signup.repository.VerificationCodeRepository;
import lombok.RequiredArgsConstructor;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.User;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final VerificationCodeRepository codeRepository;
    private final JDA jda;

    public ResponseEntity<?> sendVerificationCode(String discordId) {
        String code = String.format("%06d", new Random().nextInt(999999));
        LocalDateTime expiry = LocalDateTime.now().plusMinutes(5);
        VerificationCode vCode = new VerificationCode();
        vCode.setDiscordId(discordId);
        vCode.setCode(code);
        vCode.setExpiresAt(expiry);
        codeRepository.save(vCode);

        User user = jda.retrieveUserById(discordId).complete();
        user.openPrivateChannel().complete().sendMessage("인증 코드: " + code).queue();

        return ResponseEntity.ok("인증 코드가 전송되었습니다.");
    }

    public ResponseEntity<?> verifyCode(String discordId, String code) {
        Optional<VerificationCode> opt = codeRepository.findById(discordId);
        if (opt.isEmpty() || opt.get().getExpiresAt().isBefore(LocalDateTime.now())
            || !opt.get().getCode().equals(code)) {
            return ResponseEntity.badRequest().body("인증 실패");
        }
        return ResponseEntity.ok("인증 성공");
    }
}