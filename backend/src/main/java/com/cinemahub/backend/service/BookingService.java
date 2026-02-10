package com.cinemahub.backend.service;

import java.util.List;

import com.cinemahub.backend.model.Booking;

public interface BookingService {
    Booking createBooking(Long showId, List<Long> seatIds, Long userId);

    Booking confirmBooking(Long bookingId);

    void expirePendingBookings();

    Booking getBookingById(Long bookingId);
}
