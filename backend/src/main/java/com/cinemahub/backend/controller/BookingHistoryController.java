package com.cinemahub.backend.controller;


import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cinemahub.backend.dto.BookingHistoryDTO;
import com.cinemahub.backend.service.BookingHistoryService;

@RestController
@RequestMapping("/bookings")
public class BookingHistoryController {

    private final BookingHistoryService bookingHistoryService;

    public BookingHistoryController(BookingHistoryService bookingHistoryService) {
        this.bookingHistoryService = bookingHistoryService;
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<BookingHistoryDTO>> getUserBookings(
        @PathVariable Long userId) {
            List<BookingHistoryDTO> bookings = 
                    bookingHistoryService.getBookingsByUserId(userId);

            return ResponseEntity.ok(bookings);
    }  
    
    @GetMapping("/{bookingId}/user/{userId}")
    public ResponseEntity<BookingHistoryDTO> getBookingDetails(
        @PathVariable Long bookingId,
        @PathVariable Long userId) {

            BookingHistoryDTO booking = 
                    bookingHistoryService.getBookingDetails(bookingId, userId);
            
            return ResponseEntity.ok(booking);
    }
}
