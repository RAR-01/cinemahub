package com.cinemahub.backend.controller;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cinemahub.backend.dto.ShowDto;
import com.cinemahub.backend.model.Movie;
import com.cinemahub.backend.model.Show;
import com.cinemahub.backend.model.Theatre;
import com.cinemahub.backend.service.ShowService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/shows")
public class ShowController {

    private final ShowService showService;

    public ShowController(ShowService showService) {
        this.showService = showService;
    }


    @PostMapping
    public ShowDto createShow(@RequestBody ShowDto dto) {

        Show show = showService.createShow(
                dto.getMovieId(),
                dto.getScreenId(),
                dto.getStartTime()
        );

        return new ShowDto(
                show.getId(),
                show.getMovie().getId(),
                show.getTheatre().getId(),
                show.getScreen().getId(),
                show.getStartTime(),
                show.getEndTime()
        );
    }

    @GetMapping
    public List<ShowDto> getAllShows() {
        return showService.getAllShows()
                .stream()
                .map(show -> new ShowDto(
                        show.getId(),
                        show.getMovie().getId(),
                        show.getTheatre().getId(),
                        show.getScreen().getId(),
                        show.getStartTime(),
                        show.getEndTime()
                ))
                .toList();
    }


    @GetMapping("/movie/{movieId}")
    public List<ShowDto> getShowsByMovie(@PathVariable Long movieId) {

        Movie movie = new Movie();
        movie.setId(movieId);

        return showService.getShowsByMovie(movie)
                .stream()
                .map(show -> new ShowDto(
                        show.getId(),
                        show.getMovie().getId(),
                        show.getTheatre().getId(),
                        show.getScreen().getId(),
                        show.getStartTime(),
                        show.getEndTime()
                ))
                .toList();
    }

    @GetMapping("/theatre/{theatreId}")
    public List<ShowDto> getShowsByTheatre(@PathVariable Long theatreId) {

        Theatre theatre = new Theatre();
        theatre.setId(theatreId);

        return showService.getShowsByTheatre(theatre)
                .stream()
                .map(show -> new ShowDto(
                        show.getId(),
                        show.getMovie().getId(),
                        show.getTheatre().getId(),
                        show.getScreen().getId(),
                        show.getStartTime(),
                        show.getEndTime()
                ))
                .toList();
    }
}

