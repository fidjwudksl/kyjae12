package com.example.signup;

import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.JDA;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SignupProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(SignupProjectApplication.class, args);
    }

    @Bean
    public JDA jda() throws Exception {
        return JDABuilder.createDefault("MTM1OTU2MTU1OTgzOTQ3MzY5NQ.G70Moc.viPn8ogyPHBxoBxP4YSegpD0WXYQj1hPjO88Ko").build().awaitReady();
    }
}