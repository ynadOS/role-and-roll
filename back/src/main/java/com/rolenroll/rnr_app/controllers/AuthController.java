package com.rolenroll.rnr_app.controllers;

import com.rolenroll.rnr_app.dto.UserDTO;
import com.rolenroll.rnr_app.dto.auth.AuthRequestDTO;
import com.rolenroll.rnr_app.dto.auth.AuthResponseDTO;
import com.rolenroll.rnr_app.dto.auth.RegisterRequestDTO;
import com.rolenroll.rnr_app.entities.User;
import com.rolenroll.rnr_app.mappers.UserMapper;
import com.rolenroll.rnr_app.repositories.UserRepository;
import com.rolenroll.rnr_app.security.JwtService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@Tag(name = "Authentication", description = "Endpoints for user registration and login")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private JwtService jwtService;

    @Operation(summary = "Register a new user", description = "Creates a new user account with encoded password and returns the user data")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "User registered successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid registration data")
    })
    @PostMapping("/register")
    public ResponseEntity<UserDTO> register(@RequestBody RegisterRequestDTO request) {
        User user = new User();
        user.setName(request.name());
        user.setEmail(request.email());
        user.setPassword(passwordEncoder.encode(request.password()));

        User savedUser = userRepository.save(user);
        return ResponseEntity.ok(UserMapper.toDto(savedUser));
    }

    @Operation(summary = "User login", description = "Authenticates a user and returns a JWT token if credentials are valid")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Login successful, token returned"),
            @ApiResponse(responseCode = "401", description = "Invalid username or password"),
            @ApiResponse(responseCode = "404", description = "User not found")
    })
    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> login(@RequestBody AuthRequestDTO request) {
        User user = userRepository.findByName(request.username())
                .or(() -> userRepository.findByEmail(request.username()))
                .orElseThrow(() -> new UsernameNotFoundException("Utilisateur non trouvé"));

        if (!passwordEncoder.matches(request.password(), user.getPassword())) {
            throw new BadCredentialsException("Mot de passe invalide");
        }

        String token = jwtService.generateToken(user);
        return ResponseEntity.ok(new AuthResponseDTO(token));
    }

    @Operation(summary = "Connected user ID", description = "Return the connected user ID")
    @GetMapping("/me")
    public ResponseEntity<Map<String, Object>> getCurrentUser(@RequestHeader("Authorization") String authHeader) {
        String token = authHeader.replace("Bearer ", "");
        Long userId = jwtService.extractUserId(token);
        User user = userRepository.findById(userId).orElseThrow(() -> new UsernameNotFoundException("Utilisateur non trouvé"));

        Map<String, Object> userInfo = new HashMap<>();
        userInfo.put("id", userId);
        userInfo.put("name", user.getName());

        return ResponseEntity.ok(userInfo);
    }

    @GetMapping("/test-auth")
    public ResponseEntity<?> test(Authentication authentication) {
        if (authentication == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("No auth found");
        }

        System.out.println("User = " + authentication.getName());
        System.out.println("Authorities = " + authentication.getAuthorities());

        return ResponseEntity.ok("You are authenticated!");
    }

}
