package com.cinemahub.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.cinemahub.backend.dto.PaymentResponse;
import com.cinemahub.backend.model.Payment;
import com.cinemahub.backend.service.PaymentService;
// import com.cinemahub.backend.service.DummyPaymentGatewayService;

@RestController
@RequestMapping("/payments")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    // @Autowired
    // private DummyPaymentGatewayService dummyPaymentGatewayService;

    @PostMapping("/initiate/{bookingId}")
    public ResponseEntity<PaymentResponse> initiatePayment(
            @PathVariable Long bookingId) {

        Payment payment = paymentService.initiatePayment(bookingId);

        PaymentResponse response = new PaymentResponse(
                payment.getId(),
                payment.getBooking().getId(),
                payment.getStatus(),
                payment.getAmount()
        );

        return ResponseEntity.ok(response);
    }    
    
    @PostMapping("/{paymentId}/success")
    public ResponseEntity<String> paymentSuccess(@PathVariable Long paymentId) {

        paymentService.handlePaymentSuccess(paymentId);
        return ResponseEntity.ok("Payment successful");
    }

    @PostMapping("/{paymentId}/failure")
    public ResponseEntity<String> paymentFailure(@PathVariable Long paymentId) {

        paymentService.handlePaymentFailure(paymentId);
        return ResponseEntity.ok("Payment failed");
    }

}
