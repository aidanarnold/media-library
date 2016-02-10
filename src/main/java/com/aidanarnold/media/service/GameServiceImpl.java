package com.aidanarnold.media.service;

import com.aidanarnold.media.model.Game;
import com.aidanarnold.media.repository.GameRepository;
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
public class GameServiceImpl implements GameService {

	@Autowired
	private GameRepository gameRepository;
	
	public Game getGame(Integer id) {
		return gameRepository.findOne(id);
	}

	public void upsertGame(Game game) {
		gameRepository.save(game);
	}

	public List<Game> listGames() {
		return gameRepository.findAll();
	}

	public void deleteGame(Game game) {
		gameRepository.delete(game);
		
	}
}
