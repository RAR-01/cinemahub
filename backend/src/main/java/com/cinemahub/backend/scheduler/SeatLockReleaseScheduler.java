package com.cinemahub.backend.scheduler;

import java.time.LocalDateTime;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.cinemahub.backend.repository.SeatRepository;
import com.cinemahub.enums.SeatStatus;

import jakarta.transaction.Transactional;

@Component
public class SeatLockReleaseScheduler {
    
    private final SeatRepository seatRepository;

    public SeatLockReleaseScheduler(SeatRepository seatRepository){
        this.seatRepository = seatRepository;
    }

    @Scheduled(fixedRate = 60000)
    @Transactional
    public void releaseExpiredSeatLocks() {

        int releasedSeats = seatRepository.releaseExpiredLocks(
            SeatStatus.LOCKED,
            SeatStatus.AVAILABLE,
            LocalDateTime.now()
        );
        
        if (releasedSeats > 0) {
            System.out.println("Released " + releasedSeats + " expired seat locks");
        }
    }
}
