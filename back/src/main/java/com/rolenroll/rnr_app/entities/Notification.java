package com.rolenroll.rnr_app.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import jakarta.persistence.Id;

@Entity
@Table(name = "notifications")
public class Notification extends AuditableEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(optional = false)
    private User user;

    @Column(nullable = false)
    private String message;

    @Column(nullable = false)
    private String type; // e.g. "INVITATION", "STORY", "COMMENT", "UPDATE"

    @Column(name = "related_campaign_id")
    private Long relatedCampaignId;

    @Column(nullable = false)
    private boolean read = false;

    public Notification() {
    }

    public Notification(User user, String message, String type, Long relatedCampaignId) {
        this.user = user;
        this.message = message;
        this.type = type;
        this.relatedCampaignId = relatedCampaignId;
        this.read = false;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getRelatedCampaignId() {
        return relatedCampaignId;
    }

    public void setRelatedCampaignId(Long relatedCampaignId) {
        this.relatedCampaignId = relatedCampaignId;
    }

    public boolean isRead() {
        return read;
    }

    public void setRead(boolean read) {
        this.read = read;
    }
}