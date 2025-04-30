package com.rolenroll.rnr_app.repositories;

import com.rolenroll.rnr_app.entities.Invitation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvitationRepository extends JpaRepository<Invitation, Long> {
    // Ajoute ici des requêtes custom si besoin (ex: findByStatus, etc.)
}