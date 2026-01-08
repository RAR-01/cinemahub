package com.cinemahub.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cinemahub.backend.model.Movie;

public interface MovieRepository extends JpaRepository<Movie, Long> {

}
