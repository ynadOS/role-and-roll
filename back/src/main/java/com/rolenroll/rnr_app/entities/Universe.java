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

    @Column(columnDefinition = "TEXT")
    private String rules;

    @OneToMany(mappedBy = "universe")
    private List<CharacterClass> classes;

    @OneToMany(mappedBy = "universe")
    private List<Race> races;

    // Constructors
    public Universe() {}

    public Universe(Long id, String name, String description, String rules) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.rules = rules;
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

    public String getRules() {
        return rules;
    }

    public void setRules(String rules) {
        this.rules = rules;
    }

    public List<CharacterClass> getClasses() {
        return classes;
    }

    public void setClasses(List<CharacterClass> classes) {
        this.classes = classes;
    }

    public List<Race> getRaces() {
        return races;
    }

    public void setRaces(List<Race> races) {
        this.races = races;
    }
}