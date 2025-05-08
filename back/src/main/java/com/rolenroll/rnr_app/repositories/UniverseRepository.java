package com.rolenroll.rnr_app.repositories;

import com.rolenroll.rnr_app.entities.Universe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface UniverseRepository extends JpaRepository<Universe, Long> {
    List<Universe> findByCreatedById(Long userId);
    boolean existsByName(String name);
}
