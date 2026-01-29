package com.cinemahub.backend.dto;

public class TheatreDto {

    private Long id;
    private String name;
    private String city;

    public TheatreDto() {
    }

    public TheatreDto(Long id, String name, String city) {
        this.id = id;
        this.name = name;
        this.city = city;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCity() {
        return city;
    }
}
