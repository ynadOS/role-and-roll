package com.rolenroll.rnr_app.mappers;

import com.rolenroll.rnr_app.dto.UniverseDTO;
import com.rolenroll.rnr_app.entities.Universe;
import org.springframework.stereotype.Component;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@Component
public class UniverseMapper {

    public UniverseDTO toDTO(Universe universe) {
        ZonedDateTime createdAt = universe.getCreatedAt() != null
                ? universe.getCreatedAt().atZone(ZoneId.systemDefault())
                : null;

        ZonedDateTime updatedAt = universe.getUpdatedAt() != null
                ? universe.getUpdatedAt().atZone(ZoneId.systemDefault())
                : null;

        return new UniverseDTO(
                universe.getId(),
                universe.getName(),
                universe.getDescription(),
                universe.getRules(),
                createdAt,
                updatedAt,
                universe.getCreatedBy() != null ? universe.getCreatedBy().getId() : null,
                universe.getCreatedBy() != null ? universe.getCreatedBy().getName() : null,
                universe.getUpdatedBy() != null ? universe.getUpdatedBy().getId() : null,
                universe.getUpdatedBy() != null ? universe.getUpdatedBy().getName() : null
        );
    }

    public Universe toEntity(UniverseDTO dto) {
        if (dto == null) {
            return null;
        }
        Universe universe = new Universe();
        universe.setName(dto.name());
        universe.setDescription(dto.description());
        universe.setRules(dto.rules());
        return universe;
    }
}
