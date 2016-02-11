package com.aidanarnold.media.controller;

import com.aidanarnold.media.model.Movie;
import com.aidanarnold.media.service.MovieService;
import mockit.Injectable;
import mockit.NonStrictExpectations;
import mockit.Tested;
import mockit.Verifications;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class MovieControllerTest {

    @Tested
    MovieController movieController;

    @Injectable
    MovieService movieService;

    Movie movie1 = new Movie("Spy");
    Movie movie2 = new Movie("Up");
    Movie movie3 = new Movie("Watchmen");
    List<Movie> movies = new ArrayList<>();

    @Before
    public void init() {
        movieController = new MovieController();
        movie1.setId(1);
        movies.add(movie1);
        movies.add(movie2);
    }

    @Test
    public void testShouldGetMovie() {
        new NonStrictExpectations() {{
            movieService.getMovie(anyInt);
            result = movie1;
        }};

        Movie movieReturned = movieController.getMovie(1);

        new Verifications() {{
            movieService.getMovie(1);
            assertThat(movieReturned).isNotNull();
            assertThat(movieReturned.getTitle()).isEqualTo(movie1.getTitle());
            assertThat(movieReturned.getYear()).isEqualTo(movie1.getYear());
            assertThat(movieReturned.isWatched()).isEqualTo(movie1.isWatched());
        }};
    }

    @Test
    public void testShouldGetmovies() {

        new NonStrictExpectations()
        {{
            movieService.listMovies();
            result = movies;
        }};

        List<Movie> moviesReturned = movieController.movies();

        new Verifications()
        {{
            assertThat(moviesReturned.size()).isEqualTo(2);
        }};
    }

    @Test
    public void testShouldUpsertMovie() {
        movieController.addMovie(movie2);

        new Verifications() {{
            movieService.upsertMovie(movie2);
        }};
    }

    @Test
    public void testShouldDeleteMovie() {

        new NonStrictExpectations() {{
            movieService.getMovie(anyInt);
            result = movie1;
        }};

        movieController.deleteMovie(1);

        new Verifications() {{
            movieService.deleteMovie(movie1);
        }};
    }
}
