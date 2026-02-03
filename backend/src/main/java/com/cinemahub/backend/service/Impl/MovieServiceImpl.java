package com.cinemahub.backend.service.Impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cinemahub.backend.exception.ResourceNotFoundException;
import com.cinemahub.backend.model.Movie;
import com.cinemahub.backend.repository.MovieRepository;
import com.cinemahub.backend.service.MovieService;

@Service
public class MovieServiceImpl implements MovieService {

    private final MovieRepository movieRepository;

    public MovieServiceImpl(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @Override
    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

    @Override
    public Movie getMovieById(Long id) {
        return movieRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Movie not found")
                );
    }

    @Override
    public Movie createMovie(Movie movie) {
        return movieRepository.save(movie);
    }

    @Override
    public Movie updateMovie(Long id, Movie movie) {
        Movie existing = getMovieById(id);

        existing.setTitle(movie.getTitle());
        existing.setGenre(movie.getGenre());
        existing.setRating(movie.getRating());
        existing.setDescription(movie.getDescription());
        existing.setDuration(movie.getDuration());
        existing.setLanguage(movie.getLanguage());

        return movieRepository.save(existing);
    }

    @Override
    public void deleteMovie(Long id) {
        Movie existing = getMovieById(id);
        movieRepository.delete(existing);
    }
}
