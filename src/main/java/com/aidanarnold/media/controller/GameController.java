package com.aidanarnold.media.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.aidanarnold.media.model.Game;
import com.aidanarnold.media.service.GameService;

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
	 * Serves up games landing page with list of games
	 * @param model
	 * @return games view
	 */
	@RequestMapping(value = "/games", method = RequestMethod.GET)
	public String games(Model model) {
		logger.info("Displaying a list of games");
		List<Game> games = gameService.listGames();
		model.addAttribute("games", games);
		return "games";
	}
	
	/**
	 * Gets a new game to be used with game form
	 * @param model
	 * @return gameForm
	 */
	@RequestMapping(value = "/game/add", method = RequestMethod.GET) 
	public String addgame(Model model) {
		logger.info("Create new game and display form");
		model.addAttribute("game", new Game());
		return "gameForm";
	}

	/**
	 * Adds or updates a game according to whether ID is null
	 * @param b
	 * @param result
	 * @return redirect
	 */
	@RequestMapping(value = "/game/add", method = RequestMethod.POST)
	public String addgame(@Valid Game b, BindingResult result) {
		if (result.hasErrors()) {
			return "gameForm";
		}
		else {
			if (b.getId() != null)
				gameService.updateGame(b);
			else
				gameService.addGame(b);
		}
		
		return "redirect:/games";
	}

	/**
	 * Deletes a game by ID
	 * @param id
	 * @return redirect
	 */
	@RequestMapping(value = "/game/delete/{id}", method = RequestMethod.GET)
	public String deleteGame(@PathVariable Integer id) {
		Game b = gameService.getGame(id);
		logger.info(b.toString());
		gameService.deleteGame(b);
		return "redirect:/games";
	}

	/**
	 * Gets game by ID to update in game form
	 * @param id
	 * @param model
	 * @return gameForm
	 */
	@RequestMapping(value = "/game/update/{id}", method = RequestMethod.GET)
	public String updateGame(@PathVariable Integer id, Model model) {
		model.addAttribute("game", gameService.getGame(id));
		return "gameForm";
	}
}
