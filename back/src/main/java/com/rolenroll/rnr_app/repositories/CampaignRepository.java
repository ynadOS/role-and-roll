package com.rolenroll.rnr_app.repositories;

import com.rolenroll.rnr_app.entities.Campaign;
import com.rolenroll.rnr_app.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CampaignRepository extends JpaRepository<Campaign, Long> {
    List<Campaign> findByUser(User user);
    List<Campaign> findByUserId(Long userId);
}