package com.aidanarnold.media.repository;

import com.aidanarnold.media.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Data access interface for Movie entity
 * @author aidanarnold
 *
 */
public interface MovieRepository extends JpaRepository<Movie, Integer> {
}
