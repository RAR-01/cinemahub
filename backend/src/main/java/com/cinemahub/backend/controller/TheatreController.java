package com.cinemahub.backend.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cinemahub.backend.dto.TheatreDto;
import com.cinemahub.backend.model.Theatre;
import com.cinemahub.backend.service.TheatreService;

@RestController
@RequestMapping("/theatres")
public class TheatreController {

    private final TheatreService theatreService;

    public TheatreController(TheatreService theatreService) {
        this.theatreService = theatreService;
    }

 
    @PostMapping
    public TheatreDto addTheatre(@RequestBody TheatreDto dto) {

        Theatre theatre = new Theatre();
        theatre.setName(dto.getName());
        theatre.setCity(dto.getCity());

        Theatre saved = theatreService.addTheatre(theatre);

        return new TheatreDto(
                saved.getId(),
                saved.getName(),
                saved.getCity()
        );
    }

 
    @PostMapping("/{theatreId}/movies/{movieId}")
    public TheatreDto addMovieToTheatre(
            @PathVariable Long theatreId,
            @PathVariable Long movieId) {

        Theatre theatre = theatreService.addMovieToTheatre(theatreId, movieId);

        return new TheatreDto(
                theatre.getId(),
                theatre.getName(),
                theatre.getCity()
        );
    }


    @GetMapping
    public List<TheatreDto> getAllTheatres() {
        return theatreService.getAllTheatres()
                .stream()
                .map(theatre -> new TheatreDto(
                        theatre.getId(),
                        theatre.getName(),
                        theatre.getCity()
                ))
                .toList();
    }


    @GetMapping("/{id}")
    public TheatreDto getTheatreById(@PathVariable Long id) {
        Theatre theatre = theatreService.getTheatreById(id);
        return new TheatreDto(
                theatre.getId(),
                theatre.getName(),
                theatre.getCity()
        );
    }
}

