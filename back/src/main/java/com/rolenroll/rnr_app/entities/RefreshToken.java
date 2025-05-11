package com.rolenroll.rnr_app.entities;

import jakarta.persistence.*;
import java.time.ZonedDateTime;

@Entity
public class RefreshToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String token;

    @Column(name = "date_creation", nullable = false)
    private ZonedDateTime dateCreation = ZonedDateTime.now();

    @Column(nullable = false)
    private ZonedDateTime expiry;

    @Column(nullable = false)
    private boolean revoked = false;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    // Constructors
    public RefreshToken() {}

    public RefreshToken(Long id, String token, ZonedDateTime dateCreation, ZonedDateTime expiry, User user) {
        this.id = id;
        this.token = token;
        this.dateCreation = dateCreation;
        this.expiry = expiry;
        this.user = user;
    }

    // Getters & Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public ZonedDateTime getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(ZonedDateTime dateCreation) {
        this.dateCreation = dateCreation;
    }

    public ZonedDateTime getExpiry() {
        return expiry;
    }

    public void setExpiry(ZonedDateTime expiry) {
        this.expiry = expiry;
    }

    public boolean isRevoked() {
        return revoked;
    }

    public void setRevoked(boolean revoked) {
        this.revoked = revoked;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
