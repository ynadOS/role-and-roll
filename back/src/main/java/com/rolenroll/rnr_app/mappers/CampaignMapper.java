package com.rolenroll.rnr_app.mappers;

import com.rolenroll.rnr_app.dto.CampaignDTO;
import com.rolenroll.rnr_app.entities.Campaign;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;
import java.time.ZoneId;


@Component
public class CampaignMapper {

    public CampaignDTO toDTO(Campaign campaign) {
        ZonedDateTime createdAt = campaign.getCreatedAt() != null
                ? campaign.getCreatedAt().atZone(ZoneId.systemDefault())
                : null;

        ZonedDateTime updatedAt = campaign.getUpdatedAt() != null
                ? campaign.getUpdatedAt().atZone(ZoneId.systemDefault())
                : null;

        return new CampaignDTO(
                campaign.getId(),
                campaign.getTitle(),
                campaign.getDescription(),
                campaign.getUser().getId(),
                campaign.getUser().getName(),
                campaign.getStatus(),
                campaign.getStatus().getLabel(),
                campaign.getUniverse() != null ? campaign.getUniverse().getId() : null,
                campaign.getUniverse() != null ? campaign.getUniverse().getName() : null,
                createdAt,
                updatedAt,
                campaign.getCreatedBy() != null ? campaign.getCreatedBy().getId() : null,
                campaign.getCreatedBy() != null ? campaign.getCreatedBy().getName() : null,
                campaign.getUpdatedBy() != null ? campaign.getUpdatedBy().getId() : null,
                campaign.getUpdatedBy() != null ? campaign.getUpdatedBy().getName() : null
        );
    }
}
