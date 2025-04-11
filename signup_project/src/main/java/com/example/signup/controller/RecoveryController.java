package com.example.signup.controller;

import com.example.signup.service.RecoveryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/recovery")
@RequiredArgsConstructor
public class RecoveryController {

    private final RecoveryService recoveryService;

    @GetMapping("/find-id")
    public ResponseEntity<String> findId(@RequestParam String discordId) {
        return recoveryService.findUsernameByDiscordId(discordId);
    }

    @PostMapping("/reset-password")
    public ResponseEntity<String> resetPassword(@RequestBody PasswordResetRequest request) {
        return recoveryService.resetPassword(request.getDiscordId(), request.getNewPassword());
    }

    static class PasswordResetRequest {
        private String discordId;
        private String newPassword;
        public String getDiscordId() { return discordId; }
        public void setDiscordId(String discordId) { this.discordId = discordId; }
        public String getNewPassword() { return newPassword; }
        public void setNewPassword(String newPassword) { this.newPassword = newPassword; }
    }
}