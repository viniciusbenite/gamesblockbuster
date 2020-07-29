package com.blockbuster.gamesblockbuster.repositories;

import com.blockbuster.gamesblockbuster.models.ConcreteGame;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConcreteGameRepository  extends JpaRepository<ConcreteGame, Long> {
}
