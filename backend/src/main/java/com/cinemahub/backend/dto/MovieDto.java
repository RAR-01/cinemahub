package com.cinemahub.backend.dto;

public class MovieDto {

    private Long id;
    private String title;
    private String genre;
    private Double rating;

    public MovieDto() {
  
    }

    public MovieDto(Long id, String title, String genre, Double rating) {
        this.id = id;
        this.title = title;
        this.genre = genre;
        this.rating = rating;
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
}
