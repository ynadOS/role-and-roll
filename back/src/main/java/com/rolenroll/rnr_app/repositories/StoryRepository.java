package com.rolenroll.rnr_app.repositories;

import com.rolenroll.rnr_app.entities.Story;
import com.rolenroll.rnr_app.entities.Campaign;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StoryRepository extends JpaRepository<Story, Long> {
    List<Story> findByCampaign(Campaign campaign);
    List<Story> findByCampaignIdAndVisibleToPlayersTrue(Long campaignId);
}