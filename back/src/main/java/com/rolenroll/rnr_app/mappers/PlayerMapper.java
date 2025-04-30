package com.rolenroll.rnr_app.mappers;

import com.rolenroll.rnr_app.dto.PlayerDTO;
import com.rolenroll.rnr_app.entities.Player;
import org.springframework.stereotype.Component;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.Collections;

@Component
public class PlayerMapper {

    public PlayerDTO toDto(Player player) {
        ZonedDateTime createdAt = player.getCreatedAt() != null
                ? player.getCreatedAt().atZone(ZoneId.systemDefault())
                : null;

        ZonedDateTime updatedAt = player.getUpdatedAt() != null
                ? player.getUpdatedAt().atZone(ZoneId.systemDefault())
                : null;

        ZonedDateTime deathDate = player.getDeathDate() != null
                ? player.getDeathDate().atZone(ZoneId.systemDefault())
                : null;

        Set<Long> sharedWithIds = player.getSharedWith() != null
                ? player.getSharedWith().stream()
                    .map(Player::getId)
                    .collect(Collectors.toSet())
                : Collections.emptySet();

        return new PlayerDTO(
                player.getId(),
                player.getName(),
                player.getUser() != null ? player.getUser().getId() : null,
                player.getUser() != null ? player.getUser().getName() : null,
                player.getCampaign().getId(),
                player.getCampaign().getTitle(),
                player.getRace() != null ? player.getRace().getId() : null,
                player.getRace() != null ? player.getRace().getName() : null,
                player.getCharacterClass() != null ? player.getCharacterClass().getId() : null,
                player.getCharacterClass() != null ? player.getCharacterClass().getName() : null,
                deathDate,
                player.isDead(),
                createdAt,
                updatedAt,
                player.getCreatedBy() != null ? player.getCreatedBy().getId() : null,
                player.getCreatedBy() != null ? player.getCreatedBy().getName() : null,
                player.getUpdatedBy() != null ? player.getUpdatedBy().getId() : null,
                player.getUpdatedBy() != null ? player.getUpdatedBy().getName() : null,
                sharedWithIds
        );
    }

    public Player toEntity(PlayerDTO dto) {
        Player player = new Player();
        player.setId(dto.id());
        player.setName(dto.name());
        player.setDeathDate(dto.deathDate() != null ? dto.deathDate().toLocalDateTime() : null);
        // ⚠️ Les relations (user, campaign, race, class) doivent être injectées dans le service
        return player;
    }
}