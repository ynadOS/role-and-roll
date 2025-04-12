package com.rolenroll.rnr_app.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "stories")
public class Story {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String content;

    @ManyToOne
    @JoinColumn(name = "universe_id", nullable = false)
    private Universe universe;

    // Constructors
    public Story() {}

    public Story(Long id, String title, String content, Universe universe) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.universe = universe;
    }

    // Getters & Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Universe getUniverse() {
        return universe;
    }

    public void setUniverse(Universe universe) {
        this.universe = universe;
    }

    @Entity
    @Table(name = "races")
    public static class Race {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Column(nullable = false)
        private String name;

        @Column(columnDefinition = "TEXT")
        private String description;

        // Constructors
        public Race() {}

        public Race(Long id, String name, String description) {
            this.id = id;
            this.name = name;
            this.description = description;
        }

        // Getters & Setters

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }
    }
}
