package com.cinemahub.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.cinemahub.backend.dto.ScreenRequest;
import com.cinemahub.backend.model.Screen;
import com.cinemahub.backend.service.ScreenService;

@RestController
@RequestMapping("/screens")
public class ScreenController {

    @Autowired
    private ScreenService screenService;

    @PostMapping
    public ResponseEntity<Screen> createScreen(@RequestBody ScreenRequest request) {

        Screen createdScreen = screenService.createScreen(
            request.getName(),
            request.getTheatreId()
        );

        return new ResponseEntity<>(createdScreen, HttpStatus.CREATED);
    }

    
    @GetMapping("/{id}")
    public ResponseEntity<Screen> getScreenById(@PathVariable Long id) {
        Screen screen = screenService.getScreenById(id);
        return ResponseEntity.ok(screen);
    }
}
