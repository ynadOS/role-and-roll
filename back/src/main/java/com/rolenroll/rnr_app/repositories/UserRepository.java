package com.rolenroll.rnr_app.repositories;

import com.rolenroll.rnr_app.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByName(String username);

    User findByEmail(String email);

}