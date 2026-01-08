package com.cinemahub.backend.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cinemahub.backend.model.Theatre;
import com.cinemahub.backend.service.TheatreService;

@RestController
@RequestMapping("/api/theatres")
public class TheatreController {
    private final TheatreService theatreService;

    public TheatreController(TheatreService theatreService){
        this.theatreService= theatreService;
    }

    @PostMapping
    public Theatre addTheatre(@RequestBody Theatre theatre){
        return theatreService.addTheatre(theatre);
    }

    @PostMapping("/{theatreId}/movies/{movieId}")
    public Theatre addMovieToTheatre(
        @PathVariable Long theatreId,
        @PathVariable Long movieId) {
            return theatreService.addMovieToTheatre(theatreId, movieId);
    }

    @GetMapping
    public List<Theatre> getAllTheatres(){
        return theatreService.getAllTheatres();
    }

    @GetMapping("/{id}")
    public Theatre getTheatreById(@PathVariable Long id){
        return theatreService.getTheatreById(id);
    }
}
