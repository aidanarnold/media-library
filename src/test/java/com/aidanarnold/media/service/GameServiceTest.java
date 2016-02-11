package com.aidanarnold.media.service;

import com.aidanarnold.media.model.Game;
import com.aidanarnold.media.repository.GameRepository;
import mockit.Injectable;
import mockit.NonStrictExpectations;
import mockit.Tested;
import mockit.Verifications;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class GameServiceTest {

    @Injectable
    private GameRepository gameRepository;

    @Tested
    private GameServiceImpl gameService;

    Game game1 = new Game("Super Mario");
    Game game2 = new Game("Super Mario 2");
    Game game3 = new Game("Super Mario 3");
    List<Game> games = new ArrayList<>();

    @Before
    public void init() {
        gameService = new GameServiceImpl();
        games.add(game1);
        games.add(game2);
        games.add(game3);
    }

    @Test
    public void testShouldGetBook() {

        new NonStrictExpectations() {{
            gameRepository.findOne(anyInt);
            result = game1;
        }};

        Game game = gameService.getGame(1);

        new Verifications() {{
            gameRepository.findOne(1);
            assertThat(game.getTitle()).isEqualTo(game1.getTitle());
        }};
    }

    @Test
    public void testShouldGetBooks() {

        new NonStrictExpectations() {{
            gameRepository.findAll();
            result = games;
        }};

        List<Game> gamesReturned = gameService.listGames();

        new Verifications() {{
           gameRepository.findAll();
           assertThat(gamesReturned.size()).isEqualTo(games.size());
        }};
    }

    @Test
    public void testShouldUpsertBook() {
        Game game4 = new Game("A Game For Testing");

        gameService.upsertGame(game4);

        new Verifications() {{
            gameRepository.save(game4);
        }};
    }

    @Test
    public void testShouldDeleteBook() {

        gameService.deleteGame(game3);

        new Verifications() {{
            gameRepository.delete(game3);
        }};
    }
}
