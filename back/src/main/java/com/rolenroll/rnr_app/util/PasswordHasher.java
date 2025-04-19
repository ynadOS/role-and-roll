package com.rolenroll.rnr_app.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordHasher {
    public static void main(String[] args) {
        String rawPassword = "admin"; // change ici si tu veux un autre mot de passe
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String hashed = encoder.encode(rawPassword);
        System.out.println("Mot de passe hashé : " + hashed);
    }
}
