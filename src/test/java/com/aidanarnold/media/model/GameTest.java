package com.aidanarnold.media.model;

import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class GameTest {

    public static final String TITLE = "Mario 3";

    Game game = new Game();

    @Before
    public void init()
    {
        game.setTitle(TITLE);
    }

    @Test
    public void shouldConstructGame()
    {
        Game constructedGame = new Game(TITLE);

        assertThat(constructedGame.getTitle()).isEqualTo(game.getTitle());
        assertThat(constructedGame.toString()).isNotEmpty();
    }
}
