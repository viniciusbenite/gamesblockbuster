package com.blockbuster.gamesblockbuster.repositories;

import com.blockbuster.gamesblockbuster.models.Game;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameRepository extends JpaRepository<Game, Long> {
}
