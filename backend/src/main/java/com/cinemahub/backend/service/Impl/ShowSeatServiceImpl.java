package com.cinemahub.backend.service.Impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.cinemahub.backend.model.Seat;
import com.cinemahub.backend.model.Show;
import com.cinemahub.backend.model.ShowSeat;
import com.cinemahub.backend.repository.ShowSeatRepository;
import com.cinemahub.backend.service.ShowSeatService;
import com.cinemahub.enums.SeatStatus;

@Service
public class ShowSeatServiceImpl implements ShowSeatService {

    private final ShowSeatRepository showSeatRepository;

    public ShowSeatServiceImpl(ShowSeatRepository showSeatRepository){
        this.showSeatRepository = showSeatRepository;
    }

    @Override
    public List<ShowSeat> createShowSeats(Show show) {

        List<ShowSeat> showSeats = new ArrayList<>();
        List<Seat> seats = show.getScreen().getSeats();

        for (Seat seat : seats) {
            ShowSeat showSeat = new ShowSeat(
                show,
                seat,
                SeatStatus.AVAILABLE
            );
            showSeats.add(showSeat);
        }

        return showSeatRepository.saveAll(showSeats);
    }

    @Override
    public List<ShowSeat> getShowSeatsByShow(Show show){
        return showSeatRepository.findByShow(show);
    }
}
