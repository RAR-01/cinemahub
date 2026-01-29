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
import com.cinemahub.backend.dto.SeatResponse;
import com.cinemahub.backend.service.SeatLockService;
import com.cinemahub.backend.service.SeatService;

@RestController
@RequestMapping("/seats")
public class SeatController {

    @Autowired
    private SeatService seatService;

    @Autowired
    private SeatLockService seatLockService;

    // Generate seat layout
    @PostMapping("/layout")
    public String generateSeatLayout(@RequestBody SeatLayoutRequest request) {
        seatService.generateSeatLayout(
                request.getScreenId(),
                request.getRows(),
                request.getColumns(),
                request.getSeatType()
        );
        return "Seat layout generated successfully";
    }

    // Lock seats (used internally by booking flow)
    @PostMapping("/lock")
    public String lockSeats(@RequestBody List<Long> seatIds) {
        seatLockService.lockSeats(seatIds);
        return "Seats locked successfully";
    }

    // GET ALL SEATS (DTO-based, CLEAN RESPONSE)
    @GetMapping
    public List<SeatResponse> getAllSeats() {
        return seatService.getAllSeats()
                .stream()
                .map(seat -> new SeatResponse(
                        seat.getId(),
                        seat.getSeatNumber(),
                        seat.getRowLabel(),
                        seat.getColumnNumber(),
                        seat.getSeatType(),
                        seat.getSeatStatus(),
                        seat.getPrice()
                ))
                .toList();
    }

    // GET SEATS BY SCREEN (DTO-based)
    @GetMapping("/screen/{screenId}")
    public List<SeatResponse> getSeatsByScreen(@PathVariable Long screenId) {
        return seatService.getSeatsByScreenId(screenId)
                .stream()
                .map(seat -> new SeatResponse(
                        seat.getId(),
                        seat.getSeatNumber(),
                        seat.getRowLabel(),
                        seat.getColumnNumber(),
                        seat.getSeatType(),
                        seat.getSeatStatus(),
                        seat.getPrice()
                ))
                .toList();
    }

    //  Admin-only cleanup
    @DeleteMapping("/screen/{screenId}")
    public String deleteSeatsByScreen(@PathVariable Long screenId) {
        seatService.deleteSeatsByScreenId(screenId);
        return "All seats deleted for screen " + screenId;
    }
}
