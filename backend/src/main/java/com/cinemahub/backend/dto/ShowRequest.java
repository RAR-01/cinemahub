package com.cinemahub.backend.dto;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotNull;

public class ShowRequest {
    @NotNull(message = "Movie ID must not be null")
    private Long movieId;
    @NotNull(message = "Screen ID must not be null")
    private Long screenId;
    @NotNull(message = "Show start time must not be null")
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
