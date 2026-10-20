package com.polytech.ludotech.controller;

import com.polytech.ludotech.dto.MovieDTO;
import com.polytech.ludotech.mappers.MovieMapper;
import com.polytech.ludotech.service.MovieService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/movies")
public class MovieController {

    private final MovieService movieService;
    private final MovieMapper movieMapper;

    public MovieController(MovieService movieService, MovieMapper movieMapper) {
        this.movieService = movieService;
        this.movieMapper = movieMapper;
    }

    @GetMapping
    public List<MovieDTO> getAllMovies() {
        return movieMapper.toDTO(movieService.getAllMovies());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MovieDTO createMovie(@RequestBody MovieDTO movieDTO) {
        return movieMapper.toDTO(movieService.createMovie(movieMapper.toEntity(movieDTO)));
    }

    @PutMapping("/{name}")
    public MovieDTO editMovie(@RequestBody MovieDTO movieDTO, @PathVariable String name) {
        return movieMapper.toDTO(movieService.editMovie(movieMapper.toEntity(movieDTO), name));
    }

    @DeleteMapping("/{name}")
    public void deleteMovie(@PathVariable String name) {
        movieService.deleteMovie(name);
    }
}
