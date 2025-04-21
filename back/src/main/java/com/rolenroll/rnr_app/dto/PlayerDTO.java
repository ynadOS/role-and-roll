package com.rolenroll.rnr_app.dto;

import java.time.ZonedDateTime;
import java.util.Set;

public record PlayerDTO(
        Long id,
        String name,
        Long userId,
        String userName,
        Long campaignId,
        String campaignTitle,
        Long raceId,
        String raceName,
        Long classId,
        String className,
        ZonedDateTime deathDate,
        boolean isDead,
        ZonedDateTime createdAt,
        ZonedDateTime updatedAt,
        Long createdById,
        String createdByName,
        Long updatedById,
        String updatedByName,
        Set<Long> sharedWithIds // 👈 nouveau champ
) {}