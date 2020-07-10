package com.blockbuster.gamesblockbuster.repositories;

import com.blockbuster.gamesblockbuster.models.Platform;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlatformRepository extends JpaRepository<Platform, Long> {
}
