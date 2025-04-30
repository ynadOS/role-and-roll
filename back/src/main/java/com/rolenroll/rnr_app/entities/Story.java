package com.rolenroll.rnr_app.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "stories")
public class Story extends AuditableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    @ManyToOne(optional = false)
    @JoinColumn(name = "campaign_id", nullable = false)
    private Campaign campaign;

    @Column(name = "is_visible_to_players", nullable = false)
    private boolean visibleToPlayers = false;

    // Constructors

    public Story() {}

    public Story(Long id, String title, String content, Campaign campaign, boolean visibleToPlayers) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.campaign = campaign;
        this.visibleToPlayers = visibleToPlayers;
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

    public Campaign getCampaign() {
        return campaign;
    }

    public void setCampaign(Campaign campaign) {
        this.campaign = campaign;
    }

    public boolean isVisibleToPlayers() {
        return visibleToPlayers;
    }

    public void setVisibleToPlayers(boolean visibleToPlayers) {
        this.visibleToPlayers = visibleToPlayers;
    }
}