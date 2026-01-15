package com.cinemahub.backend.service;

import com.cinemahub.backend.model.Screen;

public interface ScreenService {

    Screen createScreen(Screen screen);

    Screen getScreenById(Long screenId);

}
