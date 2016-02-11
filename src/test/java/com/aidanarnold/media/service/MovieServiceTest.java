package com.aidanarnold.media.service;

import com.aidanarnold.media.model.Movie;
import com.aidanarnold.media.repository.MovieRepository;
import mockit.Injectable;
import mockit.NonStrictExpectations;
import mockit.Tested;
import mockit.Verifications;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class MovieServiceTest {

    @Injectable
    private MovieRepository movieRepository;

    @Tested
    private MovieServiceImpl movieService;

    Movie movie1 = new Movie("Spy");
    Movie movie2 = new Movie("Up");
    Movie movie3 = new Movie("Watchmen");
    List<Movie> movies = new ArrayList<>();

    @Before
    public void init() {
        movieService = new MovieServiceImpl();
        movies.add(movie1);
        movies.add(movie2);
        movies.add(movie3);
    }

    @Test
    public void testShouldGetBook() {

        new NonStrictExpectations() {{
            movieRepository.findOne(anyInt);
            result = movie1;
        }};

        Movie movie = movieService.getMovie(1);

        new Verifications() {{
            movieRepository.findOne(1);
            assertThat(movie.getTitle()).isEqualTo(movie1.getTitle());
        }};
    }

    @Test
    public void testShouldGetBooks() {

        new NonStrictExpectations() {{
            movieRepository.findAll();
            result = movies;
        }};

        List<Movie> moviesReturned = movieService.listMovies();

        new Verifications() {{
           movieRepository.findAll();
           assertThat(moviesReturned.size()).isEqualTo(movies.size());
        }};
    }

    @Test
    public void testShouldUpsertBook() {
        Movie movie4 = new Movie("Testing: A Documentary");

        movieService.upsertMovie(movie4);

        new Verifications() {{
            movieRepository.save(movie4);
        }};
    }

    @Test
    public void testShouldDeleteBook() {

        movieService.deleteMovie(movie3);

        new Verifications() {{
            movieRepository.delete(movie3);
        }};
    }
}
