package com.cinemahub.backend.dto;

public class MovieDto {

    private Long id;
    private String title;
    private String genre;
    private Double rating;
    private String description;
    private Integer duration;
    private String language;

    public MovieDto() {}

    public MovieDto(Long id, String title, String genre, Double rating,
                    String description, Integer duration, String language) {
        this.id = id;
        this.title = title;
        this.genre = genre;
        this.rating = rating;
        this.description = description;
        this.duration = duration;
        this.language = language;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getGenre() {
        return genre;
    }

    public Double getRating() {
        return rating;
    }

    public String getDescription() {
        return description;
    }

    public Integer getDuration() {
        return duration;
    }

    public String getLanguage() {
        return language;
    }
}
