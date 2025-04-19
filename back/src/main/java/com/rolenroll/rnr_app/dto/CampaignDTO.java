package com.rolenroll.rnr_app.dto;

public record CampaignDTO(
        Long id,
        String title,
        String description,
        Long userId,
        String userName,
        Long statusId,
        String statusName,
        Long universeId,
        String universeName
) {}
