package com.rolenroll.rnr_app.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "universes")
public class Universe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;

    @OneToMany(mappedBy = "universe")
    private List<CharacterClass> classes;

    @OneToMany(mappedBy = "universe")
    private List<Race> races;

    // Constructors
    public Universe() {}

    public Universe(Long id, String name, String description) {
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
