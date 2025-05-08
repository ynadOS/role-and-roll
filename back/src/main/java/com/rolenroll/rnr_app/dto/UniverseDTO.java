package com.rolenroll.rnr_app.dto;

import java.time.ZonedDateTime;

public record UniverseDTO(
        Long id,
        String name,
        String description,
        String rules,
        ZonedDateTime createdAt,
        ZonedDateTime updatedAt,
        Long createdById,
        String createdByName,
        Long updatedById,
        String updatedByName
) {}
