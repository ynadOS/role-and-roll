package com.rolenroll.rnr_app.repositories;

import com.rolenroll.rnr_app.entities.Universe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UniverseRepository extends JpaRepository<Universe, Long> {
}
