package com.rolenroll.rnr_app.entities;

import jakarta.persistence.*;
import java.util.Set;

@Entity
@Table(name = "npcs")
public class NPC {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;

    @ManyToMany
    @JoinTable(
            name = "scenario_npc",
            joinColumns = @JoinColumn(name = "npc_id"),
            inverseJoinColumns = @JoinColumn(name = "scenario_id")
    )
    private Set<Scenario> scenarios;

    @ManyToOne
    @JoinColumn(name = "location_id")
    private Location location;


    // Constructors
    public NPC() {}

    public NPC(Long id, String name, String description, Set<Scenario> scenarios) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.scenarios = scenarios;
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

    public Set<Scenario> getScenarios() {
        return scenarios;
    }

    public void setScenarios(Set<Scenario> scenarios) {
        this.scenarios = scenarios;
    }
}
