package com.aidanarnold.media.service;

import java.util.List;

import com.aidanarnold.media.model.Game;

/***
 * Service layer interface, managing CRUD
 * @author aidanarnold
 *
 */
public interface GameService {
	
 public Game getGame(Integer id);
 
 public void addGame(Game game);

 public List<Game> listGames();
 
 public void deleteGame(Game game);
 
 public void updateGame(Game game);
 
}
