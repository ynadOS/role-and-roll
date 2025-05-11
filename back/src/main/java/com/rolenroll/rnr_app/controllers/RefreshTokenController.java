package com.rolenroll.rnr_app.controllers;

import com.rolenroll.rnr_app.entities.RefreshToken;
import com.rolenroll.rnr_app.services.RefreshTokenService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class RefreshTokenController {

    private final RefreshTokenService refreshTokenService;

    public RefreshTokenController(RefreshTokenService refreshTokenService) {
        this.refreshTokenService = refreshTokenService;
    }

    @PostMapping("/refresh")
    public ResponseEntity<?> refreshAccessToken(@CookieValue("refreshToken") String refreshTokenValue,
                                                HttpServletResponse response) {
        Optional<RefreshToken> optionalToken = refreshTokenService.findByToken(refreshTokenValue);

        if (optionalToken.isEmpty() || optionalToken.get().isRevoked()) {
            return ResponseEntity.status(403).body("Invalid refresh token");
        }

        RefreshToken oldToken = optionalToken.get();
        RefreshToken newToken = refreshTokenService.rotateToken(oldToken);

        Cookie cookie = new Cookie("refreshToken", newToken.getToken());
        cookie.setHttpOnly(true);
        cookie.setSecure(true); // Set to true in production with HTTPS
        cookie.setPath("/");
        cookie.setMaxAge(4 * 24 * 60 * 60); // 4 days

        response.addCookie(cookie);

        // Generate and return new access token (placeholder here)
        String newAccessToken = "newAccessTokenPlaceholder";

        return ResponseEntity.ok().body(newAccessToken);
    }
}
