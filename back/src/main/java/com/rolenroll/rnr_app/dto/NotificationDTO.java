package com.rolenroll.rnr_app.dto;

import java.time.LocalDateTime;

public record NotificationDTO(
    Long id,
    String message,
    String type,
    Long relatedCampaignId,
    boolean read,
    LocalDateTime createdAt,
    String createdByName
) {
}
