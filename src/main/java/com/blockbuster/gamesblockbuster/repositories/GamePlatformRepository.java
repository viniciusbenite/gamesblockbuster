package com.blockbuster.gamesblockbuster.repositories;

import com.blockbuster.gamesblockbuster.models.GamePlatform;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GamePlatformRepository extends JpaRepository<GamePlatform, Long>, GamePlatformRepositoryCustom {
}
