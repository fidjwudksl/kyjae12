package com.example.signup.controller;

import com.example.signup.dto.LoginRequest;
import com.example.signup.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class LoginController {

    private final UserService userService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        System.out.println("🔒 LoginController: /login 요청 도착");

        String token = userService.authenticateUser(
            loginRequest.getUsername(),
            loginRequest.getPassword()
        );

        if (token == null) {
            return ResponseEntity.status(401).body("로그인 실패");
        }

        ResponseCookie cookie = ResponseCookie.from("token", token)
                .httpOnly(true)
                .secure(false)
                .path("/")
                .maxAge(60 * 60)
                .build();

        return ResponseEntity.ok()
                .header(HttpHeaders.SET_COOKIE, cookie.toString())
                .body("로그인 성공");
    }
}
