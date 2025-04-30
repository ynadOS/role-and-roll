package com.rolenroll.rnr_app.dto;

import java.time.ZonedDateTime;

public record CampaignDTO(
        Long id,
        String title,
        String description,
        Long userId,
        String userName,
        Long statusId,
        String statusName,
        Long universeId,
        String universeName,
        ZonedDateTime createdAt,
        ZonedDateTime updatedAt,
        Long createdById,
        String createdByName,
        Long updatedById,
        String updatedByName
) {}