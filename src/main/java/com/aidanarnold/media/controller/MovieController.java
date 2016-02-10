package com.aidanarnold.media.controller;

import com.aidanarnold.media.model.Movie;
import com.aidanarnold.media.service.MovieService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.List;

/**
 * Handles requests for the application home page.
 */
@Controller
public class MovieController {

	private static final Logger logger = LoggerFactory
			.getLogger(MovieController.class);

	@Autowired
	private MovieService movieService;

	/**
	 * Serves up books landing page with list of books
	 * @param model
	 * @return books view
	 */
	@RequestMapping(value = "/movies", method = RequestMethod.GET)
	public String books(Model model) {
		logger.info("Displaying a list of movies");
		List<Movie> movies = movieService.listMovies();
		model.addAttribute("movies", movies);
		return "movies";
	}
	
	/**
	 * Gets a new movie to be used with movie form
	 * @param model
	 * @return movieForm
	 */
	@RequestMapping(value = "/movie/add", method = RequestMethod.GET)
	public String addMovie(Model model) {
		logger.info("Create new movie and display form");
		model.addAttribute("movie", new Movie());
		return "movieForm";
	}

	/**
	 * Adds or updates a movie according to whether ID is null
	 * @param b
	 * @param result
	 * @return redirect
	 */
	@RequestMapping(value = "/movie/add", method = RequestMethod.POST)
	public String addMovie(@Valid Movie m, BindingResult result) {
		if (result.hasErrors()) {
			return "movieForm";
		}
		else {
			movieService.upsertMovie(m);
		}
		
		return "redirect:/movies";
	}

	/**
	 * Deletes a movie by ID
	 * @param id
	 * @return redirect
	 */
	@RequestMapping(value = "/movie/delete/{id}", method = RequestMethod.GET)
	public String deleteMovie(@PathVariable Integer id) {
		Movie m = movieService.getMovie(id);
		logger.info(m.toString());
		movieService.deleteMovie(m);
		return "redirect:/movies";
	}

	/**
	 * Gets movie by ID to update in movie form
	 * @param id
	 * @param model
	 * @return movieForm
	 */
	@RequestMapping(value = "/movie/update/{id}", method = RequestMethod.GET)
	public String updateMovie(@PathVariable Integer id, Model model) {
		model.addAttribute("movie", movieService.getMovie(id));
		return "movieForm";
	}
}
