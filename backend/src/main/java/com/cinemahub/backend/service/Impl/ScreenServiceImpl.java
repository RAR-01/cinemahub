package com.cinemahub.backend.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cinemahub.backend.exception.ResourceNotFoundException;
import com.cinemahub.backend.model.Screen;
import com.cinemahub.backend.model.Theatre;
import com.cinemahub.backend.repository.ScreenRepository;
import com.cinemahub.backend.repository.TheatreRepository;
import com.cinemahub.backend.service.ScreenService;

@Service
public class ScreenServiceImpl implements ScreenService {

    @Autowired
    private ScreenRepository screenRepository;

    @Autowired
    private TheatreRepository theatreRepository;

    @Override
    public Screen createScreen(String name, Long theatreId) {

        Theatre theatre = theatreRepository.findById(theatreId)
            .orElseThrow(() ->
                new ResourceNotFoundException("Theatre not found")
            );

        Screen screen = new Screen();
        screen.setName(name);
        screen.setTheatre(theatre);

        return screenRepository.save(screen);
    }

    @Override
    public Screen getScreenById(Long screenId) {
        return screenRepository.findById(screenId)
            .orElseThrow(() ->
                new ResourceNotFoundException("Screen not found")
            );
    }
}
