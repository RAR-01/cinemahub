package com.cinemahub.backend.service.Impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cinemahub.backend.repository.ShowRepository;
import com.cinemahub.backend.service.ShowSeatService;
import com.cinemahub.backend.service.ShowService;
import com.cinemahub.backend.model.*;
@Service
public class ShowServiceImpl implements ShowService {
    
    private final ShowRepository showRepository;
    private final ShowSeatService showSeatService;

    public ShowServiceImpl(ShowRepository showRepository,
                            ShowSeatService showSeatService) {
        this.showRepository = showRepository;
        this.showSeatService = showSeatService;
    }

    @Override
    public Show createShow(Show show){
        Show savedShow = showRepository.save(show);
        showSeatService.createShowSeats(savedShow);
        return savedShow;
    }

    @Override
    public List<Show> getAllShows(){
        return showRepository.findAll();
    }

    @Override
    public List<Show> getShowsByMovie(Movie movie){
        return showRepository.findByMovie(movie);
    }

    @Override
    public List<Show> getShowsByTheatre(Theatre theatre){
        return showRepository.findByTheatre(theatre);
    }
}
