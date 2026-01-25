package com.cinemahub.backend.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cinemahub.backend.exception.ConflictException;
import com.cinemahub.backend.exception.ResourceNotFoundException;
import com.cinemahub.backend.model.Screen;
import com.cinemahub.backend.model.Seat;
import com.cinemahub.backend.repository.ScreenRepository;
import com.cinemahub.backend.repository.SeatRepository;
import com.cinemahub.backend.service.SeatService;
import com.cinemahub.enums.SeatStatus;
import com.cinemahub.enums.SeatType;

import jakarta.transaction.Transactional;

@Service
public class SeatServiceImpl implements SeatService {

    @Autowired
    private SeatRepository seatRepository;

    @Autowired
    private ScreenRepository screenRepository;

    private double getPriceForSeatType(SeatType seatType){
        return switch (seatType) {
            case REGULAR -> 150.0;
            case PREMIUM -> 250.0;
            case RECLINER -> 400.0;
        };
    }

    @Override
    public Seat createSeat(Seat seat) {

        if (seat.getPrice() == null) {
            seat.setPrice(getPriceForSeatType(seat.getSeatType()));
        }

        Long screenId = seat.getScreen().getId();

        boolean alreadyExists =
            seatRepository.existsByScreenIdAndSeatNumber(
                screenId,
                seat.getSeatNumber()
            );

        if (alreadyExists) {
            throw new ConflictException(
                "Seat " + seat.getSeatNumber() + " already exists for this screen"
            );
        }

        return seatRepository.save(seat);
    }

    @Override
    public List<Seat> getSeatsByScreenId(Long screenId) {
        return seatRepository.findByScreenId(screenId);
    }

    @Override
    public List<Seat> getAllSeats() {
        return seatRepository.findAll();
    }

    @Override
    public void deleteSeatsByScreenId(Long screenId) {
        seatRepository.deleteByScreenId(screenId);
    }

    @Override
    public void generateSeatLayout(Long screenId, int rows, int columns, SeatType seatType) {

        Screen screen = screenRepository.findById(screenId)
            .orElseThrow(() ->
                new ResourceNotFoundException("Screen not found")
            );

        double price = getPriceForSeatType(seatType);

        for (int r = 0; r < rows; r++) {
            char rowLabel = (char) ('A' + r);

            for (int c = 1; c <= columns; c++) {

                String seatNumber = rowLabel + String.valueOf(c);

                if (seatRepository.existsByScreenIdAndSeatNumber(screenId, seatNumber)) {
                    continue;
                }

                Seat seat = new Seat(
                    seatNumber,
                    String.valueOf(rowLabel),
                    c,
                    seatType,
                    SeatStatus.AVAILABLE,
                    screen,
                    price
                );

                seat.setPrice(getPriceForSeatType(seatType));
                seatRepository.save(seat);
            }
        }
    }

    @Override
    @Transactional
    public void releaseSeats(List<Seat> seats) {

        for (Seat seat : seats) {
            seat.setSeatStatus(SeatStatus.AVAILABLE);
            seat.setLockedAt(null);
            seat.setLockExpiresAt(null);
        }

        seatRepository.saveAll(seats);
    }
}
