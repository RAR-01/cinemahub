package com.cinemahub.backend.scheduler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.cinemahub.backend.service.Impl.PaymentServiceImpl;

@Component
@EnableScheduling
public class PaymentTimeoutScheduler {

    @Autowired
    private PaymentServiceImpl paymentService;

    @Scheduled(fixedRate = 60000) 
    public void expirePayments() {
        paymentService.expireInitiatedPayments();
    }
}

