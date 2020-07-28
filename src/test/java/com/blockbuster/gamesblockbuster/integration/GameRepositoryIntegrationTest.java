package com.blockbuster.gamesblockbuster.integration;

import com.blockbuster.gamesblockbuster.models.Game;
import com.blockbuster.gamesblockbuster.repositories.GameRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class) // Link between JUnit and Spring. It replaces @RunWith from JUnit 4
@DataJpaTest
public class GameRepositoryIntegrationTest {

    @Autowired
    private TestEntityManager testEntityManager;
    @Autowired
    private GameRepository gameRepository;

    @Test
    public void whenFoundByID_thenReturnGame() {
        // given
        Game fifa20 = new Game(1L, "Fifa 20", "Sport", 2019);
        testEntityManager.merge(fifa20);
        testEntityManager.flush();
        // when
        Game game = gameRepository.findById(fifa20.getId()).orElse(Game.builder().build());
        // then
        assertThat(game.getGenre()).isEqualTo(fifa20.getGenre());
    }

    @Test
    public void whenFoundByID_thenReturnCorrectTitle() {
        // given
        Game fifa19 = new Game(2L, "Fifa 19", "Sport", 2018);
        testEntityManager.merge(fifa19);
        testEntityManager.flush();
        // when
        Game game = gameRepository.findById(fifa19.getId()).orElse(Game.builder().build());
        // then
        assertThat(game.getTitle()).isEqualTo(fifa19.getTitle());
    }
}
