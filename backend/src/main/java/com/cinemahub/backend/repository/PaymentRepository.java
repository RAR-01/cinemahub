package com.cinemahub.backend.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cinemahub.backend.model.Payment;
import com.cinemahub.enums.PaymentStatus;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

    Optional<Payment> findByBookingId(Long bookingId);

    boolean existsByBookingIdAndStatus(Long bookingId, PaymentStatus status);

    List<Payment> findByStatusAndExpiresAtBefore(
        PaymentStatus status, 
        LocalDateTime time);

}
