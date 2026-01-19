package com.cinemahub.backend.service.Impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.cinemahub.backend.model.Seat;
import com.cinemahub.backend.repository.SeatRepository;
import com.cinemahub.backend.service.SeatLockService;
import com.cinemahub.enums.SeatStatus;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class SeatLockServiceImpl implements SeatLockService{
    private final SeatRepository seatRepository;

    private static final int LOCK_DURATION_MINUTES = 10;

    public SeatLockServiceImpl(SeatRepository seatRepository){
        this.seatRepository = seatRepository;
    }

    @Override
    public void lockSeats(List<Long> seatIds) {
        
        List<Seat> availableSeats = 
                    seatRepository.findByIdInAndSeatStatus(seatIds, SeatStatus.AVAILABLE);

        if (availableSeats.size() != seatIds.size()){
            throw new RuntimeException("One or more seats are already locked or booked");
        }

        LocalDateTime now = LocalDateTime.now();
        LocalDateTime expriresAt = now.plusMinutes(LOCK_DURATION_MINUTES);

        seatRepository.lockSeats(
            seatIds,
            SeatStatus.LOCKED,
            now,
            expriresAt
        );
    }

}
