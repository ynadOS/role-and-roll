package com.rolenroll.rnr_app.dto;

import java.time.ZonedDateTime;

public record StoryDTO(
        Long id,
        String title,
        String content,
        Long campaignId,
        String campaignTitle,
        boolean visibleToPlayers,
        ZonedDateTime createdAt,
        ZonedDateTime updatedAt,
        Long createdById,
        String createdByName,
        Long updatedById,
        String updatedByName
) {}