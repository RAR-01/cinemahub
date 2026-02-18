package com.cinemahub.backend.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cinemahub.backend.model.Movie;
import com.cinemahub.backend.model.Show;
import com.cinemahub.backend.model.Theatre;
import com.cinemahub.enums.ShowStatus;

@Repository
public interface ShowRepository extends JpaRepository<Show, Long>{

    List<Show> findByMovie(Movie movie);
    
    List<Show> findByTheatre(Theatre theatre);

    List<Show> findByStatusAndEndTimeBefore(
    ShowStatus status,
    LocalDateTime time
    );
}
