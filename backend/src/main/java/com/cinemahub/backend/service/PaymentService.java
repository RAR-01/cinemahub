package com.cinemahub.backend.service;

import com.cinemahub.backend.model.Payment;

public interface PaymentService {

    Payment initiatePayment(Long bookingId);

    void handlePaymentSuccess(Long paymentId);

    void handlePaymentFailure(Long paymentId);

    void expireInitiatedPayments();
}
