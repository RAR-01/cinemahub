package com.cinemahub.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cinemahub.backend.dto.SeatLayoutRequest;
import com.cinemahub.backend.model.Seat;
import com.cinemahub.backend.service.SeatLockService;
import com.cinemahub.backend.service.SeatService;

@RestController
@RequestMapping("/seats")
public class SeatController {

    @Autowired
    private SeatService seatService;

    @Autowired
    private SeatLockService seatLockService;

    @PostMapping
    public Seat createSeat(@RequestBody Seat seat) {
        return seatService.createSeat(seat);
    }

    @PostMapping("/layout")
    public String generateSeatLayout(@RequestBody SeatLayoutRequest request) {
        seatService.generateSeatLayout(request.getScreenId(),
        request.getRows(), 
        request.getColumns(), 
        request.getSeatType());
        return "Seat layout generated successfully";
    }

    @PostMapping("/lock")
    public String lockSeats(@RequestBody List<Long> seatIds) {
        seatLockService.lockSeats(seatIds);
        return "Seats locked successfully";
    }


    @GetMapping
    public List<Seat> getAllSeats(){
        return seatService.getAllSeats();
    }

    @GetMapping("/screen/{screenId}")
    public List<Seat> getSeatsByScreen(@PathVariable Long screenId){
        return seatService.getSeatsByScreenId(screenId);
    }

    @DeleteMapping("/screen/{screenId}")
    public String deleteSeatsByScreen(@PathVariable Long screenId) {
    seatService.deleteSeatsByScreenId(screenId);
    return "All seats deleted for screen " + screenId;
    }

}
