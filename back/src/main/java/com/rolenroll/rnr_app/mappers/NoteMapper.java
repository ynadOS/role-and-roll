package com.rolenroll.rnr_app.mappers;

import com.rolenroll.rnr_app.dto.NoteDTO;
import com.rolenroll.rnr_app.entities.Note;
import com.rolenroll.rnr_app.entities.Player;
import org.springframework.stereotype.Component;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class NoteMapper {

    public NoteDTO toDto(Note note) {
        ZonedDateTime createdAt = note.getCreatedAt() != null
                ? note.getCreatedAt().atZone(ZoneId.systemDefault())
                : null;

        ZonedDateTime updatedAt = note.getUpdatedAt() != null
                ? note.getUpdatedAt().atZone(ZoneId.systemDefault())
                : null;

        Set<Long> sharedIds = note.getSharedWith().stream()
                .map(Player::getId)
                .collect(Collectors.toSet());

        return new NoteDTO(
                note.getId(),
                note.getTitle(),
                note.getContent(),
                note.getAuthor().getId(),
                note.getAuthor().getName(),
                sharedIds,
                createdAt,
                updatedAt
        );
    }

    public Note toEntity(NoteDTO dto) {
        Note note = new Note();
        note.setId(dto.id());
        note.setTitle(dto.title());
        note.setContent(dto.content());
        // L'auteur et les joueurs à partager doivent être injectés via le service
        return note;
    }
}