package com.cinemahub.backend.dto;

import java.time.LocalDateTime;

public class ShowRequest {
    private Long movieId;
    private Long screenId;
    private LocalDateTime startTime;

    public Long getMovieId() {
        return movieId;
    }

    public Long getScreenId() {
        return screenId;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }
}
