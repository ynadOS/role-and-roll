package com.rolenroll.rnr_app.repositories;

import com.rolenroll.rnr_app.entities.Status;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatusRepository extends JpaRepository<Status, Long> {
}
