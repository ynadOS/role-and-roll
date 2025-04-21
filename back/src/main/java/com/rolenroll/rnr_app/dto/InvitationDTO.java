package com.rolenroll.rnr_app.dto;

import java.time.ZonedDateTime;

public record InvitationDTO(
        Long id,
        String name,
        Long statusId,
        String statusName,
        ZonedDateTime createdAt,
        ZonedDateTime updatedAt,
        Long createdById,
        String createdByName,
        Long updatedById,
        String updatedByName
) {}