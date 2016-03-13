package com.aidanarnold.media.controller;

import com.aidanarnold.media.model.Game;
import com.aidanarnold.media.service.GameService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Handles requests for the application home page.
 */
@Controller
public class GameController {

    private static final Logger logger = LoggerFactory
            .getLogger(GameController.class);

    @Autowired
    private GameService gameService;

    /**
     * Gets all games
     *
     * @return games
     */
    @RequestMapping(value = "/games", method = RequestMethod.GET)
    @ResponseBody
    public List<Game> games() {
        return gameService.listGames();
    }

    /**
     * Adds a new game
     *
     * @return games
     */
    @RequestMapping(value = "/games", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public void addGame(@RequestBody Game game) {
        gameService.upsertGame(game);
    }

    /**
     * Gets a game by ID
     *
     * @param id
     * @return game
     */
    @RequestMapping(value = "/games/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Game getGame(@PathVariable Integer id) {
        return gameService.getGame(id);
    }

    /**
     * Updates game
     *
     * @return
     */
    @RequestMapping(value = "/games/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public void updateBook(@PathVariable Integer id, @RequestBody Game game) {
        gameService.upsertGame(game);
    }

    /**
     * Deletes a game by ID
     *
     * @param id
     * @return redirect
     */
    @RequestMapping(value = "/games/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public void deleteGame(@PathVariable Integer id) {
        Game g = gameService.getGame(id);
        gameService.deleteGame(g);
    }
}
