package com.cinemahub.backend.service.Impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cinemahub.backend.model.Screen;
import com.cinemahub.backend.repository.ScreenRepository;
import com.cinemahub.backend.service.ScreenService;

@Service
public class ScreenServiceImpl implements ScreenService {

    @Autowired
    private ScreenRepository screenRepository;

    @Override
    public Screen createScreen(Screen screen) {
        return screenRepository.save(screen);
    }

    @Override
    public Screen getScreenById(Long screenId) {
        return screenRepository.findById(screenId)
            .orElseThrow(() -> new RuntimeException("Screen not found"));
    }

}
