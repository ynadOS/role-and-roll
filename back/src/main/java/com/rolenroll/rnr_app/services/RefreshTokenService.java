package com.rolenroll.rnr_app.services;

import com.rolenroll.rnr_app.entities.RefreshToken;
import com.rolenroll.rnr_app.repositories.RefreshTokenRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RefreshTokenService {

    private final RefreshTokenRepository refreshTokenRepository;

    public RefreshTokenService(RefreshTokenRepository refreshTokenRepository) {
        this.refreshTokenRepository = refreshTokenRepository;
    }

    public Optional<RefreshToken> findByToken(String token) {
        return refreshTokenRepository.findByToken(token);
    }

    public void deleteByToken(String token) {
        refreshTokenRepository.deleteByToken(token);
    }

    public RefreshToken save(RefreshToken refreshToken) {
        return refreshTokenRepository.save(refreshToken);
    }

    public RefreshToken rotateToken(RefreshToken oldToken) {
        oldToken.setRevoked(true);
        refreshTokenRepository.save(oldToken);

        RefreshToken newToken = new RefreshToken();
        newToken.setToken(java.util.UUID.randomUUID().toString());
        newToken.setUser(oldToken.getUser());
        newToken.setExpiry(java.time.ZonedDateTime.now().plusDays(4));
        newToken.setRevoked(false);
        return refreshTokenRepository.save(newToken);
    }
}
