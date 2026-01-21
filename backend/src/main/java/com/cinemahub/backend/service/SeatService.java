package com.cinemahub.backend.service;

import java.util.List;


import com.cinemahub.backend.model.Seat;
import com.cinemahub.enums.SeatType;

public interface SeatService {
    
    Seat createSeat(Seat seat);

    List<Seat> getSeatsByScreenId(Long ScreenId);

    List<Seat> getAllSeats();

    void deleteSeatsByScreenId(Long screenId);

    void generateSeatLayout(Long screenId, int rows, int columns, SeatType seatType);

    void releaseSeats(List<Seat> seats);
    
}
