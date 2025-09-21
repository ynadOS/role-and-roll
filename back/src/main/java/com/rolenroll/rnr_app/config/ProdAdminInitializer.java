package com.rolenroll.rnr_app.config;

import com.rolenroll.rnr_app.entities.User;
import com.rolenroll.rnr_app.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@Profile("prod")
public class ProdAdminInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public ProdAdminInitializer(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) {
        // Vérifie si un admin existe déjà
        if (userRepository.findByEmail("admin@rolenroll.app").isEmpty()) {
            String adminPassword = System.getenv("ADMIN_PASSWORD"); // mot de passe depuis l'environnement
            if (adminPassword == null || adminPassword.isBlank()) {
                throw new IllegalStateException("⚠️ ADMIN_PASSWORD n'est pas défini dans l'environnement !");
            }

            User admin = new User();
            admin.setName("superadmin");
            admin.setEmail("admin@rolenroll.app");
            admin.setPassword(passwordEncoder.encode(adminPassword));
            // TODO: si tu as une gestion de rôles, set le rôle ADMIN ici

            userRepository.save(admin);
            System.out.println("✅ Compte admin créé avec l'email admin@rolenroll.app");
        } else {
            System.out.println("ℹ️ Un compte admin existe déjà, rien à faire.");
        }
    }
}
