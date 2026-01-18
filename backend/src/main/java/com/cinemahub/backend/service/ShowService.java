package com.cinemahub.backend.service;

import java.time.LocalDateTime;
import java.util.List;

import com.cinemahub.backend.model.Movie;
import com.cinemahub.backend.model.Show;
import com.cinemahub.backend.model.Theatre;

public interface ShowService {
  
  Show createShow(Long movieId, Long screenId, LocalDateTime starTime);
  
  List<Show> getAllShows();

  List<Show> getShowsByMovie(Movie movie);

  List<Show> getShowsByTheatre(Theatre theatre);
}
