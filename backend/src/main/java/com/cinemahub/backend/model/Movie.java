package com.cinemahub.backend.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name = "movies")
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(unique = true)
    private String title;
    private String genre;
    private double rating;

    @Column(length = 1000)
    private String description;

    private Integer duration;   // in minutes
    private String language;

    // TMDB integration fields
    private String tmdbId;
    private String posterPath;

    @ManyToMany(mappedBy = "movies")
    @JsonIgnore
    private List<Theatre> theatres = new ArrayList<>();

    public Movie() {}

    public Movie(String title, String genre, double rating,
                 String description, Integer duration, String language,
                 String tmdbId, String posterPath) {
        this.title = title;
        this.genre = genre;
        this.rating = rating;
        this.description = description;
        this.duration = duration;
        this.language = language;
        this.tmdbId = tmdbId;
        this.posterPath = posterPath;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getTmdbId() {
        return tmdbId;
    }

    public void setTmdbId(String tmdbId) {
        this.tmdbId = tmdbId;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public List<Theatre> getTheatres() {
        return theatres;
    }
}