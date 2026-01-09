package com.cinemahub.backend.service;

import java.util.List;

import com.cinemahub.backend.model.Movie;
import com.cinemahub.backend.model.Show;
import com.cinemahub.backend.model.Theatre;

public interface ShowService {
  
  Show createShow(Show show);
  
  List<Show> getAllShows();

  List<Show> getShowsByMoive(Movie movie);

  List<Show> getShowsByTheatre(Theatre theatre);
}
