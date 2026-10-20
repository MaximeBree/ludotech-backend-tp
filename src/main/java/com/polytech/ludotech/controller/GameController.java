package com.polytech.ludotech.controller;

import com.polytech.ludotech.dto.GameDTO;
import com.polytech.ludotech.mappers.GameMapper;
import com.polytech.ludotech.service.GameService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/games")
public class GameController {

    private final GameService gameService;
    private final GameMapper gameMapper;

    public GameController(GameService gameService, GameMapper gameMapper) {
        this.gameService = gameService;
        this.gameMapper = gameMapper;
    }

    @GetMapping
    public List<GameDTO> getAllGames() {
        return gameMapper.toDTO(gameService.getAllGames());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public GameDTO createGame(@RequestBody GameDTO gameDTO) {
        return gameMapper.toDTO(gameService.createGame(gameMapper.toEntity(gameDTO)));
    }

    @PutMapping("/{name}")
    public GameDTO editGame(@RequestBody GameDTO gameDTO, @PathVariable String name) {
        return gameMapper.toDTO(gameService.editGame(gameMapper.toEntity(gameDTO), name));
    }

    @DeleteMapping("/{name}")
    public void deleteGame(@PathVariable String name) {
        gameService.deleteGame(name);
    }
}
