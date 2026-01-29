package com.cinemahub.backend.dto;

import java.time.LocalDateTime;

public class ShowDto {

    private Long id;
    private Long movieId;
    private Long theatreId;
    private Long screenId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    public ShowDto() {
    }

    public ShowDto(
            Long id,
            Long movieId,
            Long theatreId,
            Long screenId,
            LocalDateTime startTime,
            LocalDateTime endTime
    ) {
        this.id = id;
        this.movieId = movieId;
        this.theatreId = theatreId;
        this.screenId = screenId;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public Long getId() {
        return id;
    }

    public Long getMovieId() {
        return movieId;
    }

    public Long getTheatreId() {
        return theatreId;
    }

    public Long getScreenId() {
        return screenId;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }
}
