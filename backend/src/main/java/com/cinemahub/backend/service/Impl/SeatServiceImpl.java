package com.cinemahub.backend.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cinemahub.backend.model.Screen;
import com.cinemahub.backend.model.Seat;
import com.cinemahub.backend.repository.ScreenRepository;
import com.cinemahub.backend.repository.SeatRepository;
import com.cinemahub.backend.service.SeatService;
import com.cinemahub.enums.SeatStatus;
import com.cinemahub.enums.SeatType;

@Service
public class SeatServiceImpl implements SeatService {

    @Autowired
    private SeatRepository seatRepository;

    @Autowired
    private ScreenRepository screenRepository;

    @Override
    public Seat createSeat(Seat seat) {

        Long screenId = seat.getScreen().getId();

        boolean alreadyExists =
            seatRepository.existsByScreenIdAndSeatNumber(
                screenId,
                seat.getSeatNumber()
            );

        if (alreadyExists) {
            throw new RuntimeException(
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
            .orElseThrow(() -> new RuntimeException("Screen not found"));

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
                    screen
                );

                seatRepository.save(seat);
            }
        }
    }
}
