package com.rolenroll.rnr_app.entities;

public enum InvitationStatus {
    SENT("Envoyée"),
    ACCEPTED("Acceptée"),
    DECLINED("Refusée");

    private final String label;

    InvitationStatus(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}