package com.blockbuster.gamesblockbuster.repositories;

import com.blockbuster.gamesblockbuster.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
