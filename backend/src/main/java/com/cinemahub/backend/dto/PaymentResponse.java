package com.cinemahub.backend.dto;

import com.cinemahub.enums.PaymentStatus;

public class PaymentResponse {

    private Long paymentId;
    private Long bookingId;
    private PaymentStatus status;
    private Double amount;

    public PaymentResponse(
            Long paymentId,
            Long bookingId,
            PaymentStatus status,
            Double amount
    ) {
        this.paymentId = paymentId;
        this.bookingId = bookingId;
        this.status = status;
        this.amount = amount;
    }

    public Long getPaymentId() {
        return paymentId;
    }

    public Long getBookingId() {
        return bookingId;
    }

    public PaymentStatus getStatus() {
        return status;
    }

    public Double getAmount() {
        return amount;
    }

}
