package com.cinemahub.backend.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cinemahub.backend.dto.BookingResponse;
import com.cinemahub.backend.dto.CreateBookingRequest;
import com.cinemahub.backend.model.Booking;
import com.cinemahub.backend.model.Seat;
import com.cinemahub.backend.service.BookingService;

@RestController
@RequestMapping("/bookings")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @PostMapping
    public ResponseEntity<BookingResponse> createBooking(@RequestBody CreateBookingRequest request) {
        Booking booking = bookingService.createBooking(
            request.getShowId(),
            request.getSeatIds());
        List<Long> seatIds = booking.getSeats()
            .stream()
            .map(seat -> seat.getId())
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
    public ResponseEntity<BookingResponse> confirmBooking(@PathVariable Long bookingId) {

        Booking booking = bookingService.confirmBooking(bookingId);

        List<Long> seatIds = booking.getSeats()
                                .stream()
                                .map(seat -> seat.getId())
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
            BookingResponse response = new BookingResponse(booking.getId(), 
                booking.getStatus(), 
                seatIds, 
                booking.getTotalAmount()
            );

            return ResponseEntity.ok(response);
    }

}
