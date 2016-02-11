package com.aidanarnold.media.controller;

import com.aidanarnold.media.model.Movie;
import com.aidanarnold.media.service.MovieService;
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
@RequestMapping("movies")
public class MovieController {

	private static final Logger logger = LoggerFactory
			.getLogger(MovieController.class);

	@Autowired
	private MovieService movieService;

	/**
	 * Gets a movie by ID
	 * @param id
	 */
	@RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
	public Movie getMovie(@PathVariable Integer id) {
		return movieService.getMovie(id);
	}

	/**
	 * Serves up books landing page with list of books
	 *
	 * @return movies
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	@ResponseBody
	public List<Movie> movies() {
		logger.info("Displaying a list of movies");
		return movieService.listMovies();
	}

	/**
	 * Adds or updates a movie according to whether ID is null
	 * @param movie
	 */
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.OK)
	public void addMovie(Movie movie) {
		movieService.upsertMovie(movie);
	}

	/**
	 * Deletes a movie by ID
	 * @param id
	 */
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public void deleteMovie(@PathVariable Integer id) {
		Movie m = movieService.getMovie(id);
		logger.info("Attempting to delete: " + m.toString());
		movieService.deleteMovie(m);
	}
}
