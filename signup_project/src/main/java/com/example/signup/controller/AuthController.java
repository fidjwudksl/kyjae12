package com.example.signup.controller;

import com.example.signup.dto.DiscordAuthRequest;
import com.example.signup.dto.DiscordAuthVerify;
import com.example.signup.dto.SignupRequest;
import com.example.signup.service.AuthService;
import com.example.signup.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class AuthController {

    private final AuthService authService;
    private final UserService userService;

    @PostMapping("/auth/discord/request")
    public ResponseEntity<?> requestCode(@RequestBody DiscordAuthRequest request) {
        return authService.sendVerificationCode(request.getDiscordId());
    }

    @PostMapping("/auth/discord/verify")
    public ResponseEntity<?> verifyCode(@RequestBody DiscordAuthVerify verify) {
        return authService.verifyCode(verify.getDiscordId(), verify.getCode());
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody SignupRequest request) {
        return userService.registerUser(request);
    }
}