package com.aidanarnold.media.controller;

import com.aidanarnold.media.model.Game;
import com.aidanarnold.media.service.GameService;
import mockit.Injectable;
import mockit.NonStrictExpectations;
import mockit.Tested;
import mockit.Verifications;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class GameControllerTest {

    @Tested
    GameController gameController;

    @Injectable
    GameService gameService;

    Game game1 = new Game("Super Mario", "Super NES", true);
    Game game2 = new Game("Metroid 3", "Nintendo Wii", true);
    List<Game> games = new ArrayList<>();

    @Before
    public void init() {
        gameController = new GameController();
        game1.setId(1);
        games.add(game1);
        games.add(game2);
    }

    @Test
    public void testShouldGetGame() {
        new NonStrictExpectations() {{
            gameService.getGame(anyInt);
            result = game1;
        }};

        Game gameReturned = gameController.getGame(1);

        new Verifications() {{
            gameService.getGame(1);
            assertThat(gameReturned).isNotNull();
            assertThat(gameReturned.getTitle()).isEqualTo(game1.getTitle());
            assertThat(gameReturned.getPlatform()).isEqualTo(game1.getPlatform());
            assertThat(gameReturned.isPlayed()).isEqualTo(game1.isPlayed());
        }};
    }

    @Test
    public void testShouldGetgames() {

        new NonStrictExpectations()
        {{
            gameService.listGames();
            result = games;
        }};

        List<Game> gamesReturned = gameController.games();

        new Verifications()
        {{
            assertThat(gamesReturned.size()).isEqualTo(2);
        }};
    }

    @Test
    public void testShouldUpsertGame() {
        gameController.addGame(game2);

        new Verifications() {{
            gameService.upsertGame(game2);
        }};
    }

    @Test
    public void testShouldDeleteGame() {

        new NonStrictExpectations() {{
            gameService.getGame(anyInt);
            result = game1;
        }};

        gameController.deleteGame(1);

        new Verifications() {{
            gameService.deleteGame(game1);
        }};
    }
}
