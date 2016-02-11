package com.aidanarnold.media.model;

import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class GameTest {

    public static final String TITLE = "Mario 3";
    public static final String PLATFORM = "Nintendo";

    Game game = new Game();

    @Before
    public void init()
    {
        game.setTitle(TITLE);
        game.setPlatform(PLATFORM);
        game.setPlayed(true);
    }

    @Test
    public void shouldConstructGameWithTitleOnly()
    {
        Game constructedGame = new Game(TITLE);

        assertThat(constructedGame.getTitle()).isEqualTo(game.getTitle());
    }

    @Test
    public void shouldConstructGame()
    {
        Game constructedGame = new Game(TITLE, PLATFORM, true);

        assertThat(constructedGame.getTitle()).isEqualTo(game.getTitle());
        assertThat(constructedGame.getPlatform()).isEqualTo(game.getPlatform());
        assertThat(constructedGame.isPlayed()).isEqualTo(game.isPlayed());
    }
}
