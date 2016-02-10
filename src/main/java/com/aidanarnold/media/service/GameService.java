package com.aidanarnold.media.service;

import com.aidanarnold.media.model.Game;

import java.util.List;

/***
 * Service layer interface, managing CRUD
 * @author aidanarnold
 *
 */
public interface GameService {
	
 Game getGame(Integer id);
 
 void upsertGame(Game game);

 List<Game> listGames();
 
 void deleteGame(Game game);
 
}
