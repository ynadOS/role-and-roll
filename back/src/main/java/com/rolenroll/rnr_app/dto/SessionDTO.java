package com.rolenroll.rnr_app.dto;

import java.time.LocalDate;
import java.time.ZonedDateTime;

public record SessionDTO(
        Long id,
        LocalDate date,
        String summary,
        Long campaignId,
        String campaignTitle,
        ZonedDateTime createdAt,
        ZonedDateTime updatedAt,
        Long createdById,
        String createdByName,
        Long updatedById,
        String updatedByName
) {}