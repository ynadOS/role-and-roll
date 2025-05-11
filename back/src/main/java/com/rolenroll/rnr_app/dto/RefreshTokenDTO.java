package com.rolenroll.rnr_app.dto;

import java.time.ZonedDateTime;

public record RefreshTokenDTO(
    String token,
    ZonedDateTime expiry,
    boolean revoked
) {}
