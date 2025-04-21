package com.rolenroll.rnr_app.entities;

import java.util.Set;

public interface Shareable {

    Set<Player> getSharedWith();
    void setSharedWith(Set<Player> players);

    default void shareWith(Player player) {
        getSharedWith().add(player);
    }

    default void unshareWith(Player player) {
        getSharedWith().remove(player);
    }

    default boolean isSharedWith(Player player) {
        return getSharedWith().contains(player);
    }

    default boolean isShared() {
        return !getSharedWith().isEmpty();
    }

    default void clearShares() {
        getSharedWith().clear();
    }
}