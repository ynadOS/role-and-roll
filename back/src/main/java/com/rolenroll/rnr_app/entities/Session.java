package com.rolenroll.rnr_app.entities;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "session")
public class Session {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate date;

    @Column(columnDefinition = "TEXT")
    private String summary;

    @ManyToOne
    @JoinColumn(name = "campaign_id")
    private Campaign campaign;

    // Constructors
    public Session() {}

    public Session(Long id, LocalDate date, String summary, Campaign campaign) {
        this.id = id;
        this.date = date;
        this.summary = summary;
        this.campaign = campaign;
    }

    // Getters and setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public Campaign getCampaign() {
        return campaign;
    }

    public void setCampaign(Campaign campaign) {
        this.campaign = campaign;
    }
}
