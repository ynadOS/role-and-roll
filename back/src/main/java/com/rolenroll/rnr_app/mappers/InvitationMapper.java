package com.rolenroll.rnr_app.mappers;

import com.rolenroll.rnr_app.dto.InvitationDTO;
import com.rolenroll.rnr_app.entities.Invitation;
import com.rolenroll.rnr_app.entities.InvitationStatus;
import org.springframework.stereotype.Component;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@Component
public class InvitationMapper {

    public InvitationDTO toDto(Invitation invitation) {
        ZonedDateTime createdAt = invitation.getCreatedAt() != null
                ? invitation.getCreatedAt().atZone(ZoneId.systemDefault())
                : null;

        ZonedDateTime updatedAt = invitation.getUpdatedAt() != null
                ? invitation.getUpdatedAt().atZone(ZoneId.systemDefault())
                : null;

        return new InvitationDTO(
                invitation.getId(),
                invitation.getStatus(),
                invitation.getStatus().getLabel(),
                invitation.getUser() != null ? invitation.getUser().getId() : null,
                invitation.getUser() != null ? invitation.getUser().getName() : null,
                invitation.getCampaign() != null ? invitation.getCampaign().getId() : null,
                invitation.getCampaign() != null ? invitation.getCampaign().getTitle() : null,
                createdAt,
                updatedAt,
                invitation.getCreatedBy() != null ? invitation.getCreatedBy().getId() : null,
                invitation.getCreatedBy() != null ? invitation.getCreatedBy().getName() : null,
                invitation.getUpdatedBy() != null ? invitation.getUpdatedBy().getId() : null,
                invitation.getUpdatedBy() != null ? invitation.getUpdatedBy().getName() : null
        );
    }

    public Invitation toEntity(InvitationDTO dto) {
        Invitation invitation = new Invitation();
        invitation.setId(dto.id());
        invitation.setStatus(dto.status());
        // Relations user et campaign injectées dans le service
        return invitation;
    }
}