package com.cinemahub.backend.service;

import java.util.List;

import com.cinemahub.backend.model.Show;
import com.cinemahub.backend.model.ShowSeat;

public interface ShowSeatService {
    
    List<ShowSeat> createShowSeats(Show show);

    List<ShowSeat> getShowSeatsByShow(Show show);
}
