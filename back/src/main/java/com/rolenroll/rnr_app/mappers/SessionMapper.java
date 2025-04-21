package com.rolenroll.rnr_app.mappers;

import com.rolenroll.rnr_app.dto.SessionDTO;
import com.rolenroll.rnr_app.entities.Session;
import org.springframework.stereotype.Component;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@Component
public class SessionMapper {

    public SessionDTO toDto(Session session) {
        ZonedDateTime createdAt = session.getCreatedAt() != null
                ? session.getCreatedAt().atZone(ZoneId.systemDefault())
                : null;

        ZonedDateTime updatedAt = session.getUpdatedAt() != null
                ? session.getUpdatedAt().atZone(ZoneId.systemDefault())
                : null;

        return new SessionDTO(
                session.getId(),
                session.getDate(),
                session.getSummary(),
                session.getCampaign().getId(),
                session.getCampaign().getTitle(),
                createdAt,
                updatedAt,
                session.getCreatedBy() != null ? session.getCreatedBy().getId() : null,
                session.getCreatedBy() != null ? session.getCreatedBy().getName() : null,
                session.getUpdatedBy() != null ? session.getUpdatedBy().getId() : null,
                session.getUpdatedBy() != null ? session.getUpdatedBy().getName() : null
        );
    }

    public Session toEntity(SessionDTO dto) {
        Session session = new Session();
        session.setId(dto.id());
        session.setDate(dto.date());
        session.setSummary(dto.summary());
        // La campagne doit être injectée dans le service
        return session;
    }
}