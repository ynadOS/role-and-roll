package com.rolenroll.rnr_app.dto;

import java.time.ZonedDateTime;
import java.util.Set;

public record NoteDTO(
        Long id,
        String title,
        String content,
        Long authorId,
        String authorName,
        Set<Long> sharedWithPlayerIds,
        ZonedDateTime createdAt,
        ZonedDateTime updatedAt
) {}