package com.aidanarnold.media.controller;

import com.aidanarnold.media.model.Movie;
import com.aidanarnold.media.service.MovieService;
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
public class MovieController {

    private static final Logger logger = LoggerFactory
            .getLogger(MovieController.class);

    @Autowired
    private MovieService movieService;

    /**
     * Gets all movies
     *
     * @return movies
     */
    @RequestMapping(value = "/movies", method = RequestMethod.GET)
    @ResponseBody
    public List<Movie> movies() {
        return movieService.listMovies();
    }

    /**
     * Adds new movie
     */
    @RequestMapping(value = "/movies", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public void addMovie(@RequestBody Movie movie) {
        movieService.upsertMovie(movie);
    }

    /**
     * Gets movie by ID
     *
     * @return movie
     */
    @RequestMapping(value = "/movies/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Movie getMovie(@PathVariable Integer id) {
        return movieService.getMovie(id);
    }

    /**
     * Adds or updates a movie according to whether ID is null
     *
     * @param movie
     */
    @RequestMapping(value = "/movies/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public void updateMovie(@PathVariable Integer id, @RequestBody Movie movie) {
        movieService.upsertMovie(movie);
    }

    /**
     * Deletes a movie by ID
     *
     * @param id
     */
    @RequestMapping(value = "/movies/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public void deleteMovie(@PathVariable Integer id) {
        Movie m = movieService.getMovie(id);
        movieService.deleteMovie(m);
    }
}
