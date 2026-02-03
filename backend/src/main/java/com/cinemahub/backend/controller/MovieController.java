package com.cinemahub.backend.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.cinemahub.backend.dto.MovieDto;
import com.cinemahub.backend.model.Movie;
import com.cinemahub.backend.service.MovieService;

@RestController
@RequestMapping("/movies")
public class MovieController {

    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping
    public List<MovieDto> getAllMovies() {
        return movieService.getAllMovies()
                .stream()
                .map(movie -> new MovieDto(
                        movie.getId(),
                        movie.getTitle(),
                        movie.getGenre(),
                        movie.getRating(),
                        movie.getDescription(),
                        movie.getDuration(),
                        movie.getLanguage()
                ))
                .toList();
    }

    @GetMapping("/{id}")
    public MovieDto getMovieById(@PathVariable Long id) {
        Movie movie = movieService.getMovieById(id);
        return new MovieDto(
                movie.getId(),
                movie.getTitle(),
                movie.getGenre(),
                movie.getRating(),
                movie.getDescription(),
                movie.getDuration(),
                movie.getLanguage()
        );
    }

    @PostMapping
    public MovieDto createMovie(@RequestBody MovieDto dto) {
        Movie movie = new Movie(
                dto.getTitle(),
                dto.getGenre(),
                dto.getRating(),
                dto.getDescription(),
                dto.getDuration(),
                dto.getLanguage()
        );

        Movie savedMovie = movieService.createMovie(movie);

        return new MovieDto(
                savedMovie.getId(),
                savedMovie.getTitle(),
                savedMovie.getGenre(),
                savedMovie.getRating(),
                savedMovie.getDescription(),
                savedMovie.getDuration(),
                savedMovie.getLanguage()
        );
    }

    @PutMapping("/{id}")
    public MovieDto updateMovie(
            @PathVariable Long id,
            @RequestBody MovieDto dto
    ) {
        Movie movie = new Movie(
                dto.getTitle(),
                dto.getGenre(),
                dto.getRating(),
                dto.getDescription(),
                dto.getDuration(),
                dto.getLanguage()
        );

        Movie updatedMovie = movieService.updateMovie(id, movie);

        return new MovieDto(
                updatedMovie.getId(),
                updatedMovie.getTitle(),
                updatedMovie.getGenre(),
                updatedMovie.getRating(),
                updatedMovie.getDescription(),
                updatedMovie.getDuration(),
                updatedMovie.getLanguage()
        );
    }

    @DeleteMapping("/{id}")
    public void deleteMovie(@PathVariable Long id) {
        movieService.deleteMovie(id);
    }
}
