package com.cinemahub.backend.dto;

import java.util.List;

import com.cinemahub.enums.BookingStatus;

public class BookingResponse {

    private Long bookingId;
    private BookingStatus status;
    private List<Long> seatIds;

    public BookingResponse(Long bookingId, BookingStatus status, List<Long> seatIds) {
        this.bookingId = bookingId;
        this.status = status;
        this.seatIds = seatIds;
    }

    public Long getBookingId() {
        return bookingId;
    }

    public BookingStatus getStatus() {
        return status;
    }

    public List<Long> getSeatIds() {
        return seatIds;
    }
}