package com.cinemahub.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.cinemahub.backend.model.Payment;
import com.cinemahub.backend.service.PaymentService;
import com.cinemahub.backend.service.DummyPaymentGatewayService;

@RestController
@RequestMapping("/payments")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private DummyPaymentGatewayService dummyPaymentGatewayService;

    @PostMapping("/initiate/{bookingId}")
    public Payment initiatePayment(@PathVariable Long bookingId) {
        return paymentService.initiatePayment(bookingId);
    }


    @PostMapping("/pay/{paymentId}")
    public String pay(
            @PathVariable Long paymentId,
            @RequestParam boolean success) {

        dummyPaymentGatewayService.processPayment(paymentId, success);
        return "Payment processed via dummy gateway";
    }
}
