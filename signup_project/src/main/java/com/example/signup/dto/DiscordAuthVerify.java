package com.example.signup.dto;

import lombok.Data;

@Data
public class DiscordAuthVerify {
    private String discordId;
    private String code;
}