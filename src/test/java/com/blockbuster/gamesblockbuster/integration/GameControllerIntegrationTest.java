package com.blockbuster.gamesblockbuster.integration;

import com.blockbuster.gamesblockbuster.controllers.GameController;
import com.blockbuster.gamesblockbuster.models.Game;
import com.blockbuster.gamesblockbuster.repositories.GameRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(GameController.class)
public class GameControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private GameRepository gameRepository;
    
    @Test
    public void givenGames_whenGetGames_returnGamesList() throws Exception {
        Game fifa20 = new Game(1L, "Fifa 20", "Sport", 2019);
        Game fifa19 = new Game(2L, "Fifa 19", "Sport", 2018);
        List<Game> allGames = new ArrayList<>();
        allGames.add(fifa19);
        allGames.add(fifa20);
        given(gameRepository.findAll()).willReturn(allGames);

        mockMvc.perform(get("/api/game")
                .contentType(MediaType.APPLICATION_JSON))
	            .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id").value(fifa19.getId()))
                .andExpect(jsonPath("$[1].title").value(fifa20.getTitle()));
    }
}
