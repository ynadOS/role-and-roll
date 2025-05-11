package com.rolenroll.rnr_app.mappers;

import com.rolenroll.rnr_app.dto.RefreshTokenDTO;
import com.rolenroll.rnr_app.entities.RefreshToken;

public class RefreshTokenMapper {
    public static RefreshTokenDTO toDTO(RefreshToken refreshToken) {
        return new RefreshTokenDTO(
            refreshToken.getToken(),
            refreshToken.getExpiry(),
            refreshToken.isRevoked()
        );
    }

    public static RefreshToken fromDTO(RefreshTokenDTO dto) {
        RefreshToken refreshToken = new RefreshToken();
        refreshToken.setToken(dto.token());
        refreshToken.setExpiry(dto.expiry());
        refreshToken.setRevoked(dto.revoked());
        return refreshToken;
    }
}
