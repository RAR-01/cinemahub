package com.cinemahub.backend.service.Impl;

import java.util.List;


import org.springframework.stereotype.Service;

import com.cinemahub.backend.model.Movie;
import com.cinemahub.backend.model.Theatre;
import com.cinemahub.backend.repository.MovieRepository;
import com.cinemahub.backend.repository.TheatreRepository;
import com.cinemahub.backend.service.TheatreService;

@Service
public class TheatreServiceImpl implements TheatreService {
    private final TheatreRepository theatreRepository;
    private final MovieRepository movieRepository;

    public TheatreServiceImpl(TheatreRepository theatreRepository, 
                              MovieRepository movieRepository) {
        this.theatreRepository = theatreRepository;
        this.movieRepository = movieRepository;
    }

    @Override
    public Theatre addTheatre (Theatre theatre){
        return theatreRepository.save(theatre);
    }

    @Override
    public List<Theatre> getAllTheatres(){
        return theatreRepository.findAll();
    }

    @Override
    public Theatre getTheatreById(Long id) {
        return theatreRepository.findById(id).orElse(null);
    }

    @Override
    public Theatre addMovieToTheatre(Long theatreId, Long movieId) {
        Theatre theatre = theatreRepository.findById(theatreId)
                            .orElseThrow(() -> new RuntimeException("Theatre not Found"));

        Movie movie = movieRepository.findById(movieId)
                            .orElseThrow(() -> new RuntimeException("Movie not found"));
        
        theatre.getMovies().add(movie);
        return theatreRepository.save(theatre);
    }
}
