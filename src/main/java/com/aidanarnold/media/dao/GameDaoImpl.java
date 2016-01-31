package com.aidanarnold.media.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Repository;

import com.aidanarnold.media.model.Game;

/**
 * DAO Implementation for Game entity 
 * @author aidanarnold
 *
 */
@Repository("gameDao")
public class GameDaoImpl implements GameDao {
	
	private static final Logger logger = LoggerFactory.getLogger(GameDaoImpl.class);
	
	@PersistenceContext
	private EntityManager entityManager;
	
	public Game getGame(Integer id) {
		Game game = entityManager.find(Game.class, id);
		logger.info("Returning the following: " + game.toString());
		return entityManager.find(Game.class, id);
	}

	public List<Game> list() {
		logger.info("List of games from datastore.");
		String hql = "from Game";
		return entityManager.createQuery(hql, Game.class).getResultList();
	}

	public void addGame(Game game) {
		logger.info("Adding game.");
		entityManager.persist(game);
	}
	
	public void updateGame(Game game) {
		logger.info("Updating game.");
		entityManager.merge(game);
	}

	public void deleteGame(Game game) {
		logger.info("Deleting game");
		entityManager.remove(entityManager.contains(game) ? game : entityManager.merge(game));
	}
}
