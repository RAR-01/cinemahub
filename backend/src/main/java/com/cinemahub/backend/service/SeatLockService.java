package com.cinemahub.backend.service;

import java.util.List;

public interface SeatLockService {

    void lockSeats(List<Long> seatIds);
}
