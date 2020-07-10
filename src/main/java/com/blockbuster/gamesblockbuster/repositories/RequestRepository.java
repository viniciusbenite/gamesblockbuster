package com.blockbuster.gamesblockbuster.repositories;

import com.blockbuster.gamesblockbuster.models.Requests;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RequestRepository extends JpaRepository<Requests, Long> {
}
