package com.cinemahub.backend.service;

import java.util.List;

import com.cinemahub.backend.model.Theatre;

public interface TheatreService {
    Theatre addTheatre(Theatre theatre);
    
    List<Theatre> getAllTheatres();

    Theatre getTheatreById(Long id);

    Theatre addMovieToTheatre(Long theatreId, Long movieId);
}
