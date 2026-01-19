package com.cinemahub.backend.scheduler;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.cinemahub.backend.service.BookingService;

@Component
public class BookingExpiryScheduler {

    private final BookingService bookingService;

    public BookingExpiryScheduler(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @Scheduled(fixedRate = 60000) // every 60 seconds
    public void cleanupExpiredBookings() {
        bookingService.expirePendingBookings();
    }
}
