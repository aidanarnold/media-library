package com.aidanarnold.media.service;

import com.aidanarnold.media.model.Movie;

import java.util.List;

/***
 * Service layer interface, managing CRUD
 * @author aidanarnold
 *
 */
public interface MovieService {
	
 Movie getMovie(Integer id);
 
 void upsertMovie(Movie movie);

 List<Movie> listMovies();
 
 void deleteMovie(Movie movie);
}
