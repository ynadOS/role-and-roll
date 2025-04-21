package com.rolenroll.rnr_app.repositories;

import com.rolenroll.rnr_app.entities.Note;
import com.rolenroll.rnr_app.entities.Player;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NoteRepository extends JpaRepository<Note, Long> {
    List<Note> findByAuthor(Player author);
    List<Note> findBySharedWithContains(Player player);
}