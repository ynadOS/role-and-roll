package com.rolenroll.rnr_app.dto;

import com.rolenroll.rnr_app.entities.InvitationStatus;
import java.time.ZonedDateTime;

public record InvitationDTO(
        Long id,
        InvitationStatus status,
        String statusLabel,
        Long userId,
        String userName,
        Long campaignId,
        String campaignTitle,
        ZonedDateTime createdAt,
        ZonedDateTime updatedAt,
        Long createdById,
        String createdByName,
        Long updatedById,
        String updatedByName
) {}