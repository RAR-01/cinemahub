package com.cinemahub.backend.service;

import java.util.List;

import com.cinemahub.backend.model.Movie;

public interface MovieService {
    List<Movie> getAllMovies();
    
    Movie getMovieById(Long id);

    Movie createMovie(Movie movie);

    Movie updateMovie(Long id, Movie movie);
    
    void deleteMovie(Long id);
}
