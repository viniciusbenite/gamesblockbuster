package com.blockbuster.gamesblockbuster;

import com.blockbuster.gamesblockbuster.controllers.GameController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class SmokeTest {

    @Autowired
    private GameController gameController;

    @Test
    void smokeTest() throws Exception{
        assertThat(gameController).isNotNull();
    }
}