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
                        movie.getRating()
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
                movie.getRating()
        );
    }


    @PostMapping
    public MovieDto createMovie(@RequestBody MovieDto dto) {
        Movie movie = new Movie(
                dto.getTitle(),
                dto.getGenre(),
                dto.getRating()
        );

        Movie savedMovie = movieService.createMovie(movie);

        return new MovieDto(
                savedMovie.getId(),
                savedMovie.getTitle(),
                savedMovie.getGenre(),
                savedMovie.getRating()
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
                dto.getRating()
        );

        Movie updatedMovie = movieService.updateMovie(id, movie);

        return new MovieDto(
                updatedMovie.getId(),
                updatedMovie.getTitle(),
                updatedMovie.getGenre(),
                updatedMovie.getRating()
        );
    }


    @DeleteMapping("/{id}")
    public void deleteMovie(@PathVariable Long id) {
        movieService.deleteMovie(id);
    }
}
