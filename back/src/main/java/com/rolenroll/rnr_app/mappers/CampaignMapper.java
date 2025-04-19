package com.rolenroll.rnr_app.mappers;

import com.rolenroll.rnr_app.dto.CampaignDTO;
import com.rolenroll.rnr_app.entities.Campaign;
import org.springframework.stereotype.Component;

@Component
public class CampaignMapper {

    public CampaignDTO toDTO(Campaign campaign) {
        return new CampaignDTO(
                campaign.getId(),
                campaign.getTitle(),
                campaign.getDescription(),
                campaign.getUser().getId(),
                campaign.getUser().getName(),
                campaign.getStatus().getId(),
                campaign.getStatus().getName(),
                campaign.getUniverse() != null ? campaign.getUniverse().getId() : null,
                campaign.getUniverse() != null ? campaign.getUniverse().getName() : null
        );
    }
}
