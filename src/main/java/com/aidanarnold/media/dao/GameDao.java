package com.aidanarnold.media.dao;

import java.util.List;

import com.aidanarnold.media.model.Game;
 
/**
 * Data access interface for Games entity
 * @author aidanarnold
 *
 */
public interface GameDao {
 
	public Game getGame(Integer id);
	public List<Game> list();
	public void addGame(Game game);
	public void deleteGame(Game game);
	public void updateGame(Game game);
	
}
