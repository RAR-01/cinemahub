package com.cinemahub.backend.service;

import com.cinemahub.backend.model.Screen;

public interface ScreenService {

    Screen createScreen(String name, Long theatreId);

    Screen getScreenById(Long screenId);

}
