package com.rolenroll.rnr_app.util;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class PasswordGeneratorRunner {

    @Bean
    public CommandLineRunner generateHash(PasswordEncoder encoder) {
        return args -> {
            String rawPassword = "admin";
            String hash = encoder.encode(rawPassword);
            System.out.println(">>> Mot de passe hashé pour 'admin' : " + hash);
            String rawPasswordPlayer = "player";
            String hashPlayer = encoder.encode(rawPasswordPlayer);
            System.out.println(">>> Mot de passe hashé pour 'player' : " + hashPlayer);
            String rawPasswordPlayer2 = "player2";
            String hashPlayer2 = encoder.encode(rawPasswordPlayer2);
            System.out.println(">>> Mot de passe hashé pour 'player2' : " + hashPlayer2);
        };
    }
}
