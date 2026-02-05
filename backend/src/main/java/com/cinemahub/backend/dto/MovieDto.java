package com.cinemahub.backend.dto;

public class MovieDto {

    private Long id;
    private String title;
    private String genre;
    private Double rating;
    private String description;
    private Integer duration;
    private String language;

    // TMDB fields
    private String tmdbId;
    private String posterPath;

    public MovieDto() {}

    public MovieDto(Long id, String title, String genre, Double rating,
                    String description, Integer duration, String language,
                    String tmdbId, String posterPath) {
        this.id = id;
        this.title = title;
        this.genre = genre;
        this.rating = rating;
        this.description = description;
        this.duration = duration;
        this.language = language;
        this.tmdbId = tmdbId;
        this.posterPath = posterPath;
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

    public String getTmdbId() {
        return tmdbId;
    }

    public String getPosterPath() {
        return posterPath;
    }
}
