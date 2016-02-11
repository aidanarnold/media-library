package com.aidanarnold.media.model;

import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Java6Assertions.assertThat;

public class MovieTest {

    private final static String TITLE = "Trainwreck";
    private final static String YEAR = "2015";

    private Movie movie = new Movie();

    @Before
    public void init() {
        movie.setTitle(TITLE);
        movie.setYear(YEAR);
        movie.setWatched(true);
    }

    @Test
    public void testShouldConstructMovieWithTitleOnly()
    {
        Movie constructedMovie = new Movie(TITLE);

        assertThat(constructedMovie).isNotNull();
        assertThat(constructedMovie.getTitle()).isEqualTo(movie.getTitle());
    }

    @Test
    public void testShouldConstructMovie()
    {
        Movie constructedMovie = new Movie(TITLE, YEAR, true);

        assertThat(constructedMovie).isNotNull();
        assertThat(constructedMovie.getTitle()).isEqualTo(movie.getTitle());
        assertThat(constructedMovie.getYear()).isEqualTo(movie.getYear());
        assertThat(constructedMovie.isWatched()).isEqualTo(movie.isWatched());
    }
}
