package com.cinemahub.backend.controller;

import java.util.List;

import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.cinemahub.backend.dto.BookingResponse;
import com.cinemahub.backend.dto.CreateBookingRequest;
import com.cinemahub.backend.model.Booking;
import com.cinemahub.backend.model.Seat;
import com.cinemahub.backend.service.BookingService;

@RestController
@RequestMapping("/bookings")
public class BookingController {

    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @PostMapping
    public ResponseEntity<BookingResponse> createBooking(
            @Valid @RequestBody CreateBookingRequest request) {

        Booking booking = bookingService.createBooking(
                request.getShowId(),
                request.getSeatIds(),
                request.getUserId()
        );

        List<Long> seatIds = booking.getSeats()
                .stream()
                .map(Seat::getId)
                .toList();

        BookingResponse response = new BookingResponse(
                booking.getId(),
                booking.getStatus(),
                seatIds,
                booking.getTotalAmount()
        );

        return ResponseEntity.ok(response);
    }

    @PostMapping("/{bookingId}/confirm")
    public ResponseEntity<BookingResponse> confirmBooking(
            @PathVariable Long bookingId) {

        Booking booking = bookingService.confirmBooking(bookingId);

        List<Long> seatIds = booking.getSeats()
                .stream()
                .map(Seat::getId)
                .toList();

        BookingResponse response = new BookingResponse(
                booking.getId(),
                booking.getStatus(),
                seatIds,
                booking.getTotalAmount()
        );

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{bookingId}")
    public ResponseEntity<BookingResponse> getBooking(
            @PathVariable Long bookingId) {

        Booking booking = bookingService.getBookingById(bookingId);

        List<Long> seatIds = booking.getSeats()
                .stream()
                .map(Seat::getId)
                .toList();

        BookingResponse response = new BookingResponse(
                booking.getId(),
                booking.getStatus(),
                seatIds,
                booking.getTotalAmount()
        );

        return ResponseEntity.ok(response);
    }
}
