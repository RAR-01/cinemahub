package com.cinemahub.backend.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class ScreenRequest {

    @NotBlank(message = "Screen name must not be blank")
    private String name;
    @NotNull(message = "Theatre ID must not be null")
    private Long theatreId;

    public String getName() {
        return name;
    }

    public Long getTheatreId() {
        return theatreId;
    }
}