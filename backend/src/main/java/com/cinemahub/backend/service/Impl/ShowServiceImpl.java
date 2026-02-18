package com.cinemahub.backend.service.Impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.cinemahub.backend.exception.ResourceNotFoundException;
import com.cinemahub.backend.model.Movie;
import com.cinemahub.backend.model.Screen;
import com.cinemahub.backend.model.Show;
import com.cinemahub.backend.model.Theatre;
import com.cinemahub.backend.repository.MovieRepository;
import com.cinemahub.backend.repository.ScreenRepository;
import com.cinemahub.backend.repository.ShowRepository;
import com.cinemahub.backend.repository.TheatreRepository;
import com.cinemahub.backend.service.ShowSeatService;
import com.cinemahub.backend.service.ShowService;
import com.cinemahub.enums.ShowStatus;   // ✅ ADDED

@Service
public class ShowServiceImpl implements ShowService {

    private final ShowRepository showRepository;
    private final MovieRepository movieRepository;
    private final ScreenRepository screenRepository;
    // private final TheatreRepository theatreRepository;
    private final ShowSeatService showSeatService;

    public ShowServiceImpl(
            ShowRepository showRepository,
            MovieRepository movieRepository,
            ScreenRepository screenRepository,
            TheatreRepository theatreRepository,
            ShowSeatService showSeatService) {

        this.showRepository = showRepository;
        this.movieRepository = movieRepository;
        this.screenRepository = screenRepository;
        // this.theatreRepository = theatreRepository;
        this.showSeatService = showSeatService;
    }

    @Override
    public Show createShow(Long movieId, Long screenId, LocalDateTime startTime) {

        Movie movie = movieRepository.findById(movieId)
            .orElseThrow(() ->
                new ResourceNotFoundException("Movie not found")
            );

        Screen screen = screenRepository.findById(screenId)
            .orElseThrow(() ->
                new ResourceNotFoundException("Screen not found")
            );

        Theatre theatre = screen.getTheatre();

        Show show = new Show();
        show.setMovie(movie);
        show.setScreen(screen);
        show.setTheatre(theatre);
        show.setStartTime(startTime);
        show.setEndTime(startTime.plusHours(3));

        show.setStatus(ShowStatus.SCHEDULED);   // ✅ ADDED

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
