package com.rolenroll.rnr_app.dto;

public record CampaignDTO(
        Long id,
        String title,
        String description,
        Long userId,
        Long statusId,
        String statusName,
        Long universeId,
        String universeName
) {}
