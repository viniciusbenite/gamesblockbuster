package com.blockbuster.gamesblockbuster.unit;

import com.blockbuster.gamesblockbuster.models.Game;
import com.blockbuster.gamesblockbuster.repositories.GameRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CreateGameTest {

    @Mock
    private GameRepository gameRepository;

    private Game game;

    @BeforeEach
    void setup() {
        game = new Game(1L, "F12020", "Racing", 2020);
    }

    @Test
    public void savedGameHasTitleTest() {
        when(gameRepository.save(any(Game.class))).then(returnsFirstArg());
        Game savedGame = gameRepository.save(game);
        assertThat(savedGame.getTitle()).isNotNull();
    }

    @Test
    public void savedGameTitleMatch() {
        when(gameRepository.save(any(Game.class))).then(returnsFirstArg());
        Game savedGame = gameRepository.save(game);
        assertThat(savedGame.getTitle()).isEqualTo(game.getTitle());
    }
}
