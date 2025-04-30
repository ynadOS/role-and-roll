package com.rolenroll.rnr_app.entities;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "notes")
public class Note extends AuditableEntity implements Shareable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    @ManyToOne(optional = false)
    @JoinColumn(name = "author_id", nullable = false)
    private Player author;

    @ManyToMany
    @JoinTable(
            name = "note_shared_with_players",
            joinColumns = @JoinColumn(name = "note_id"),
            inverseJoinColumns = @JoinColumn(name = "player_id")
    )
    private Set<Player> sharedWith = new HashSet<>();

    // Constructors
    public Note() {}

    public Note(String title, String content, Player author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }

    // Getters & Setters
    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }

    public void setTitle(String title) { this.title = title; }

    public String getContent() { return content; }

    public void setContent(String content) { this.content = content; }

    public Player getAuthor() { return author; }

    public void setAuthor(Player author) { this.author = author; }

    @Override
    public Set<Player> getSharedWith() { return sharedWith; }

    @Override
    public void setSharedWith(Set<Player> sharedWith) { this.sharedWith = sharedWith; }
}