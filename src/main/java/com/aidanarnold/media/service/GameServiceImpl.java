package com.aidanarnold.media.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aidanarnold.media.dao.GameDao;
import com.aidanarnold.media.model.Game;

/***
 * Service layer implementation, managing CRUD
 * @author aidanarnold
 *
 */
@Service("gameService")
@Transactional
public class GameServiceImpl implements GameService {

	@Autowired
	private GameDao gameDao;
	
	public Game getGame(Integer id) {
		return gameDao.getGame(id);
	}

	public void addGame(Game game) {
		gameDao.addGame(game);
	}

	public List<Game> listGames() {
		return gameDao.list();
	}

	public void deleteGame(Game game) {
		gameDao.deleteGame(game);
		
	}

	public void updateGame(Game game) {
		gameDao.updateGame(game);
		
	}

}
