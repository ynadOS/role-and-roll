package com.rolenroll.rnr_app.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "invitation")
public class Invitation extends AuditableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "status_id")
    private StatusInvitation status;

    public Invitation() {
    }

    public Invitation(Long id, String name, StatusInvitation status) {
        this.id = id;
        this.name = name;
        this.status = status;
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

    public StatusInvitation getStatus() {
        return status;
    }

    public void setStatus(StatusInvitation status) {
        this.status = status;
    }
}