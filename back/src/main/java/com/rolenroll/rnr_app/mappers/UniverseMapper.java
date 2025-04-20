package com.rolenroll.rnr_app.mappers;

import com.rolenroll.rnr_app.dto.UniverseDTO;
import com.rolenroll.rnr_app.entities.Universe;

public class UniverseMapper {

    public static UniverseDTO toDTO(Universe universe) {
        if (universe == null) {
            return null;
        }
        return new UniverseDTO(
            universe.getId(),
            universe.getName(),
            universe.getDescription()
        );
    }

    public static Universe toEntity(UniverseDTO dto) {
        if (dto == null) {
            return null;
        }
        Universe universe = new Universe();
        universe.setId(dto.id()); // Only set ID if needed
        universe.setName(dto.name());
        universe.setDescription(dto.description());
        return universe;
    }
}
