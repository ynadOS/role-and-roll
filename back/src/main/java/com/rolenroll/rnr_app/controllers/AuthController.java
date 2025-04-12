package com.rolenroll.rnr_app.controllers;

import com.rolenroll.rnr_app.dto.*;
import com.rolenroll.rnr_app.dto.auth.AuthRequestDTO;
import com.rolenroll.rnr_app.dto.auth.AuthResponseDTO;
import com.rolenroll.rnr_app.dto.auth.RegisterRequestDTO;
import com.rolenroll.rnr_app.entities.User;
import com.rolenroll.rnr_app.repositories.UserRepository;
import com.rolenroll.rnr_app.security.JwtService;
import com.rolenroll.rnr_app.mappers.UserMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private JwtService jwtService;

    @PostMapping("/register")
    public ResponseEntity<UserDTO> register(@RequestBody RegisterRequestDTO request) {
        User user = new User();
        user.setName(request.name());
        user.setEmail(request.email());
        user.setPassword(passwordEncoder.encode(request.password()));

        User savedUser = userRepository.save(user);
        return ResponseEntity.ok(UserMapper.toDto(savedUser));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> login(@RequestBody AuthRequestDTO request) {
        Authentication auth = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.username(), // ✅ plus request.email()
                        request.password()
                )
        );

        User user = userRepository.findByName(request.username())
                .or(() -> userRepository.findByEmail(request.username()))
                .orElseThrow(() -> new UsernameNotFoundException("Utilisateur non trouvé"));

        String token = jwtService.generateToken(user);
        return ResponseEntity.ok(new AuthResponseDTO(token));
    }

}
