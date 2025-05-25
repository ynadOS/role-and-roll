package com.rolenroll.rnr_app.entities;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@MappedSuperclass
public abstract class ShareableWithPlayers {

    @ManyToMany
    @JoinTable(
            name = "shared_with_players",
    joinColumns = @JoinColumn(name = "entity_id"), // à adapter dans l'entité concrète
    inverseJoinColumns = @JoinColumn(name = "player_id")
    )
    protected Set<Player> sharedWith = new HashSet<>();

    public Set<Player> getSharedWith() {
        return sharedWith;
    }

    public void setSharedWith(Set<Player> sharedWith) {
        this.sharedWith = sharedWith;
    }

    public void shareWith(Player player) {
        this.sharedWith.add(player);
    }

    public void unshareWith(Player player) {
        this.sharedWith.remove(player);
    }

    public boolean isSharedWith(Player player) {
        return sharedWith.contains(player);
    }
}