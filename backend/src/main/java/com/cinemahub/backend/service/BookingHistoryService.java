package com.cinemahub.backend.service;

import java.util.List;

import com.cinemahub.backend.dto.BookingHistoryDTO;

public interface BookingHistoryService {
    
    List<BookingHistoryDTO> getBookingsByUserId(Long userId);

    BookingHistoryDTO getBookingDetails(Long bookingId, Long userId);
}
