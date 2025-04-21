package com.rolenroll.rnr_app.mappers;

import com.rolenroll.rnr_app.dto.InvitationDTO;
import com.rolenroll.rnr_app.entities.Invitation;
import com.rolenroll.rnr_app.entities.StatusInvitation;
import com.rolenroll.rnr_app.entities.User;
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
                invitation.getName(),
                invitation.getStatus() != null ? invitation.getStatus().getId() : null,
                invitation.getStatus() != null ? invitation.getStatus().getName() : null,
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
        invitation.setName(dto.name());
        // Les relations (status, user) sont à injecter dans le service
        return invitation;
    }

    public Invitation toEntityWithStatus(InvitationDTO dto, StatusInvitation status) {
        Invitation invitation = toEntity(dto);
        invitation.setStatus(status);
        return invitation;
    }
}