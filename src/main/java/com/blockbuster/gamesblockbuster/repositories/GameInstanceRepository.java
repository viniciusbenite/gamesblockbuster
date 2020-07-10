package com.blockbuster.gamesblockbuster.repositories;

import com.blockbuster.gamesblockbuster.models.GameInstance;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameInstanceRepository extends JpaRepository<GameInstance, Long> {
}
