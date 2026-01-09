package com.cinemahub.backend.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cinemahub.backend.repository.ShowRepository;
import com.cinemahub.backend.model.*;
@Service
public class ShowServiceImpl implements ShowService {
    
    private final ShowRepository showRepository;

    public ShowServiceImpl(ShowRepository showRepository) {
        this.showRepository = showRepository;
    }

    @Override
    public Show createShow(Show show){
        return showRepository.save(show);
    }

    @Override
    public List<Show> getAllShows(){
        return showRepository.findAll();
    }

    @Override
    public List<Show> getShowsByMoive(Movie movie){
        return showRepository.findByMovie(movie);
    }

    @Override
    public List<Show> getShowsByTheatre(Theatre theatre){
        return showRepository.findByTheatre(theatre);
    }
}
