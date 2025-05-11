package com.rolenroll.rnr_app.repositories;

import com.rolenroll.rnr_app.entities.Invitation;
import com.rolenroll.rnr_app.entities.Campaign;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface InvitationRepository extends JpaRepository<Invitation, Long> {
    // Ajoute ici des requêtes custom si besoin (ex: findByStatus, etc.)
    List<Invitation> findByCampaign(Campaign campaign);
}