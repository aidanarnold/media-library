package com.aidanarnold.media.controller;

import com.aidanarnold.media.model.Game;
import com.aidanarnold.media.service.GameService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Handles requests for the application home page.
 */
@Controller
@RequestMapping("games")
public class GameController {

	private static final Logger logger = LoggerFactory
			.getLogger(GameController.class);

	@Autowired
	private GameService gameService;

	/**
	 * Gets a game by ID
	 * @param id
	 * @return game
	 */
	@RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
	@ResponseBody
	public Game getGame(@PathVariable Integer id) {
		return gameService.getGame(id);
	}

	/**
	 * Serves up games landing page with list of games
	 * @return games
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	@ResponseBody
	public List<Game> games() {
		logger.info("Displaying a list of games");
		return gameService.listGames();
	}
	
	/**
	 * Gets a new game to be used with game form
	 * @return gameForm
	 */
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public void addGame(Game game) {
		logger.info("Create new game and display form");
		gameService.upsertGame(game);
	}

	/**
	 * Deletes a game by ID
	 * @param id
	 * @return redirect
	 */
	@RequestMapping(value = "/game/delete/{id}", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public void deleteGame(@PathVariable Integer id) {
		Game g = gameService.getGame(id);
		logger.info("Attempting to delete: " + g.toString());
		gameService.deleteGame(g);
	}
}
