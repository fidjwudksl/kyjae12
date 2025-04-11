package com.example.signup.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class VerificationCode {
    @Id
    private String discordId;
    private String code;
    private LocalDateTime expiresAt;
}