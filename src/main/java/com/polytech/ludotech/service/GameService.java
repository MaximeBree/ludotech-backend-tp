package com.polytech.ludotech.service;

import com.polytech.ludotech.entity.GameEntity;
import com.polytech.ludotech.entity.Status;
import com.polytech.ludotech.repository.GameRepository;
import com.polytech.ludotech.shared.GameAlreadyExistsException;
import com.polytech.ludotech.shared.GameNotExistException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class GameService {

    private final GameRepository gameRepository;
    private final KpiService kpiService;

    public GameService(GameRepository gameRepository, KpiService kpiService) {
        this.gameRepository = gameRepository;
        this.kpiService = kpiService;
    }

    public List<GameEntity> getAllGames() {
        return gameRepository.findAll();
    }

    @Transactional
    public GameEntity createGame(GameEntity game) {
        if(gameRepository.findByName(game.getName()) != null)
            throw new GameAlreadyExistsException("Game "+game.getName()+ " already exists");
        GameEntity saved = gameRepository.save(game);
        kpiService.modifierJeu(saved.getStatus(), 1);
        return saved;
    }

    @Transactional
    public GameEntity editGame(GameEntity game, String name) {
        GameEntity existing = gameRepository.findByName(name);
        if(existing == null)
            throw new GameNotExistException("Game " + name + " does not exist");
        Status ancienStatus = existing.getStatus();

        existing.setName(game.getName());
        existing.setTime(game.getTime());
        existing.setStatus(game.getStatus());
        existing.setNumber_players(game.getNumber_players());

        if (ancienStatus != game.getStatus()) {
            kpiService.modifierJeu(ancienStatus, -1);
            kpiService.modifierJeu(game.getStatus(), 1);
        }

        return gameRepository.save(existing);
    }

    @Transactional
    public void deleteGame(String name) {
        GameEntity existing = gameRepository.findByName(name);

        if(existing == null)
            throw new GameNotExistException("Game " + name + " does not exist");

        gameRepository.delete(existing);
        kpiService.modifierJeu(existing.getStatus(), -1);
    }
}
