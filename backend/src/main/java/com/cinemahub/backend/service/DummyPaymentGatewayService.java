package com.cinemahub.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DummyPaymentGatewayService {

    @Autowired
    private PaymentService paymentService;

    public void processPayment(Long paymentId, boolean success) {

        if (success) {
            paymentService.handlePaymentSuccess(paymentId);
        } else {
            paymentService.handlePaymentFailure(paymentId);
        }
    }
}
