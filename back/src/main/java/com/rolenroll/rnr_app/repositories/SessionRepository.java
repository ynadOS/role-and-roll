package com.rolenroll.rnr_app.repositories;

import com.rolenroll.rnr_app.entities.Session;
import com.rolenroll.rnr_app.entities.Campaign;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SessionRepository extends JpaRepository<Session, Long> {
    List<Session> findByCampaign(Campaign campaign);
}