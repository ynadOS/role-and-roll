package com.rolenroll.rnr_app.dto;

import com.rolenroll.rnr_app.entities.CampaignStatus;
import java.time.ZonedDateTime;

public record CampaignDTO(
        Long id,
        String title,
        String description,
        Long userId,
        String userName,
        CampaignStatus status,
        String statusLabel,
        Long universeId,
        String universeName,
        ZonedDateTime createdAt,
        ZonedDateTime updatedAt,
        Long createdById,
        String createdByName,
        Long updatedById,
        String updatedByName
) {}