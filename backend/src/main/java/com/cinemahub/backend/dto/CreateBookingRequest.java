package com.cinemahub.backend.dto;

import java.util.List;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;



public class CreateBookingRequest {

    @NotNull(message = "Show ID must not be null")
    private Long showId;
    @NotEmpty(message = "At least one seat must be selected")
    private List<Long> seatIds;

    public Long getShowId() {
        return showId;
    }

    public void setShowId(Long showId) {
        this.showId = showId;
    }

    public List<Long> getSeatIds() {
        return seatIds;
    }

    public void setSeatIds(List<Long> seatIds) {
        this.seatIds = seatIds;
    }
}
