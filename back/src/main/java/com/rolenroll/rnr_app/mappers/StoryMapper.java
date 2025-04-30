package com.rolenroll.rnr_app.mappers;

import com.rolenroll.rnr_app.dto.StoryDTO;
import com.rolenroll.rnr_app.entities.Story;
import org.springframework.stereotype.Component;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@Component
public class StoryMapper {

    public StoryDTO toDto(Story story) {
        ZonedDateTime createdAt = story.getCreatedAt() != null
                ? story.getCreatedAt().atZone(ZoneId.systemDefault())
                : null;

        ZonedDateTime updatedAt = story.getUpdatedAt() != null
                ? story.getUpdatedAt().atZone(ZoneId.systemDefault())
                : null;

        return new StoryDTO(
                story.getId(),
                story.getTitle(),
                story.getContent(),
                story.getCampaign().getId(),
                story.getCampaign().getTitle(),
                story.isVisibleToPlayers(),
                createdAt,
                updatedAt,
                story.getCreatedBy() != null ? story.getCreatedBy().getId() : null,
                story.getCreatedBy() != null ? story.getCreatedBy().getName() : null,
                story.getUpdatedBy() != null ? story.getUpdatedBy().getId() : null,
                story.getUpdatedBy() != null ? story.getUpdatedBy().getName() : null
        );
    }

    public Story toEntity(StoryDTO dto) {
        Story story = new Story();
        story.setId(dto.id());
        story.setTitle(dto.title());
        story.setContent(dto.content());
        story.setVisibleToPlayers(dto.visibleToPlayers());
        // Le setCampaign doit être fait dans le service via repository
        return story;
    }
}