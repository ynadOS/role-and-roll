package com.rolenroll.rnr_app.repositories;

import com.rolenroll.rnr_app.entities.Campaign;
import com.rolenroll.rnr_app.entities.Player;
import com.rolenroll.rnr_app.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlayerRepository extends JpaRepository<Player, Long> {
    List<Player> findByUser(User user);
    List<Player> findByCampaign(Campaign campaign);
    List<Player> findBySharedWithContaining(Player player);
    List<Player> findByUserIsNull();
    List<Player> findByUserIsNotNull();
}
