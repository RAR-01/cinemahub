package com.cinemahub.backend.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cinemahub.backend.model.Movie;
import com.cinemahub.backend.model.Show;
import com.cinemahub.backend.model.Theatre;
import com.cinemahub.backend.service.ShowService;

@RestController
@RequestMapping("/shows")
public class ShowController {
    private final ShowService showService;

    public ShowController(ShowService showService){
        this.showService = showService;
    }

    @PostMapping
    public Show createShow(@RequestBody Show show){
        return showService.createShow(show);
    }

    @GetMapping
    public List<Show> getAllShows(){
        return showService.getAllShows();
    }

    @PostMapping("/movie")
    public List<Show> getShowsByMovie(@RequestBody Movie movie){
        return showService.getShowsByMoive(movie);
    }

    @PostMapping("/theatre")
    public List<Show> getShowsByTheatre(@RequestBody Theatre theatre){
        return showService.getShowsByTheatre(theatre);
    }    
}
