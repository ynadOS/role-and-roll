package com.rolenroll.rnr_app.entities;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "players")
public class Player extends AuditableEntity implements Shareable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(optional = false)
    @JoinColumn(name = "campaign_id")
    private Campaign campaign;

    @ManyToOne
    @JoinColumn(name = "race_id")
    private Race race;

    @ManyToOne
    @JoinColumn(name = "class_id")
    private CharacterClass characterClass;

    @OneToMany(mappedBy = "player", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<File> files;

    @Column(name = "death_date")
    private LocalDateTime deathDate;

    @ManyToMany
    @JoinTable(
            name = "player_shared_with_players",
            joinColumns = @JoinColumn(name = "player_id"),
            inverseJoinColumns = @JoinColumn(name = "shared_with_player_id")
    )
    private Set<Player> sharedWith = new HashSet<>();

    // Constructors

    public Player() {}

    public Player(Long id, String name, User user, Campaign campaign, Race race, CharacterClass characterClass) {
        this.id = id;
        this.name = name;
        this.user = user;
        this.campaign = campaign;
        this.race = race;
        this.characterClass = characterClass;
    }

    // Getters & Setters

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    public Campaign getCampaign() { return campaign; }
    public void setCampaign(Campaign campaign) { this.campaign = campaign; }

    public Race getRace() { return race; }
    public void setRace(Race race) { this.race = race; }

    public CharacterClass getCharacterClass() { return characterClass; }
    public void setCharacterClass(CharacterClass characterClass) { this.characterClass = characterClass; }

    public List<File> getFiles() { return files; }
    public void setFiles(List<File> files) { this.files = files; }

    public LocalDateTime getDeathDate() { return deathDate; }
    public void setDeathDate(LocalDateTime deathDate) { this.deathDate = deathDate; }

    public boolean isDead() {
        return this.deathDate != null;
    }

    // Shareable implementation

    @Override
    public Set<Player> getSharedWith() {
        return sharedWith;
    }

    @Override
    public void setSharedWith(Set<Player> players) {
        this.sharedWith = players;
    }
}