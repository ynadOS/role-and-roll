package com.rolenroll.rnr_app.entities;

public enum CampaignStatus {
    DRAFT("Brouillon"),
    IN_PROGRESS("En cours"),
    COMPLETED("Terminé");

    private final String label;

    CampaignStatus(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}