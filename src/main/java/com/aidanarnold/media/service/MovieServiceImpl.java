package com.aidanarnold.media.service;

import com.aidanarnold.media.model.Movie;
import com.aidanarnold.media.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/***
 * Service layer implementation, managing CRUD
 * @author aidanarnold
 *
 */
@Service
@Transactional
public class MovieServiceImpl implements MovieService {

	@Autowired
	private MovieRepository movieRepository;
	
	public Movie getMovie(Integer id) {
		return movieRepository.findOne(id);
	}

	public void upsertMovie(Movie movie) {
		movieRepository.save(movie);
	}

	public List<Movie> listMovies() {
		return movieRepository.findAll();
	}

	public void deleteMovie(Movie movie) {
		movieRepository.delete(movie);
		
	}
}
