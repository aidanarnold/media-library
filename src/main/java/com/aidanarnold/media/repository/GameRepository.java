package com.aidanarnold.media.repository;

import com.aidanarnold.media.model.Game;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Data access interface for Games entity
 * @author aidanarnold
 *
 */
public interface GameRepository extends JpaRepository<Game, Integer> {

}
