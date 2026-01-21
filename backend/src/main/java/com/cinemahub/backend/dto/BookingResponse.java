package com.cinemahub.backend.dto;

import java.util.List;

import com.cinemahub.enums.BookingStatus;

public class BookingResponse {

    private Long bookingId;
    private BookingStatus status;
    private List<Long> seatIds;
    private Double totalAmount;
    
    public BookingResponse(Long bookingId, BookingStatus status, List<Long> seatIds, Double totalAmount) {
        this.bookingId = bookingId;
        this.status = status;
        this.seatIds = seatIds;
        this.totalAmount = totalAmount;
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

    public void setBookingId(Long bookingId) {
        this.bookingId = bookingId;
    }

    public void setStatus(BookingStatus status) {
        this.status = status;
    }

    public void setSeatIds(List<Long> seatIds) {
        this.seatIds = seatIds;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }
}