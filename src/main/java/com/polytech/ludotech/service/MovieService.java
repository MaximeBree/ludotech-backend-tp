package com.polytech.ludotech.service;

import com.polytech.ludotech.entity.MovieEntity;
import com.polytech.ludotech.entity.Status;
import com.polytech.ludotech.repository.MovieRepository;
import com.polytech.ludotech.shared.MovieAlreadyExistsException;
import com.polytech.ludotech.shared.MovieNotExistException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MovieService {

    private final MovieRepository movieRepository;
    private final KpiService kpiService;

    public MovieService(MovieRepository movieRepository, KpiService kpiService) {
        this.movieRepository = movieRepository;
        this.kpiService = kpiService;
    }

    public List<MovieEntity> getAllMovies() {
        return movieRepository.findAll();
    }

    @Transactional
    public MovieEntity createMovie(MovieEntity movie) {
        if(movieRepository.findByName(movie.getName()) != null)
            throw new MovieAlreadyExistsException("Movie "+movie.getName()+ " already exists");
        MovieEntity saved = movieRepository.save(movie);
        kpiService.modifierFilm(saved.getStatus(), 1);
        return saved;
    }

    @Transactional
    public MovieEntity editMovie(MovieEntity movie, String name) {
        MovieEntity existing = movieRepository.findByName(name);
        if(existing == null)
            throw new MovieNotExistException("Movie " + name + " does not exist");

        Status ancienStatus = existing.getStatus();
        existing.setName(movie.getName());
        existing.setDirector(movie.getDirector());
        existing.setGenre(movie.getGenre());
        existing.setStatus(movie.getStatus());
        existing.setDuration(movie.getDuration());

        if (ancienStatus != movie.getStatus()) {
            kpiService.modifierFilm(ancienStatus, -1);
            kpiService.modifierFilm(movie.getStatus(), 1);
        }

        return movieRepository.save(existing);
    }

    @Transactional
    public void deleteMovie(String name) {
        MovieEntity existing = movieRepository.findByName(name);

        if(existing == null)
            throw new MovieNotExistException("Movie " + name + " does not exist");

        movieRepository.delete(existing);
        kpiService.modifierFilm(existing.getStatus(), -1);
    }
}
