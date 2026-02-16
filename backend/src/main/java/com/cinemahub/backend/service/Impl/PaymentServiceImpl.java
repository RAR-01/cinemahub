package com.cinemahub.backend.service.Impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cinemahub.backend.exception.ConflictException;
import com.cinemahub.backend.exception.ResourceNotFoundException;
import com.cinemahub.backend.model.Booking;
import com.cinemahub.backend.model.Payment;
import com.cinemahub.backend.model.Seat;
import com.cinemahub.backend.repository.BookingRepository;
import com.cinemahub.backend.repository.PaymentRepository;
import com.cinemahub.backend.service.PaymentService;
import com.cinemahub.backend.service.SeatService;
import com.cinemahub.enums.BookingStatus;
import com.cinemahub.enums.PaymentStatus;
import com.cinemahub.enums.SeatStatus;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private SeatService seatService;

    @Override
    public Payment initiatePayment(Long bookingId) {

        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Booking not found")
                );

        if (booking.getStatus() != BookingStatus.PENDING_PAYMENT) {
            throw new ConflictException("Booking not eligible for payment");
        }

        return paymentRepository.findByBookingId(bookingId)
                .orElseGet(() -> {

                    Payment payment = new Payment();
                    payment.setBooking(booking);

                    Double amount = booking.getTotalAmount();
                    if (amount == null) {
                        throw new ConflictException("Booking total amount is not set");
                    }

                    payment.setAmount(amount);
                    payment.setStatus(PaymentStatus.INITIATED);

                    LocalDateTime now = LocalDateTime.now();
                    payment.setCreatedAt(now);
                    payment.setUpdatedAt(now);
                    payment.setExpiresAt(now.plusMinutes(5));

                    return paymentRepository.save(payment);
                });
    }

    @Override
    public void handlePaymentSuccess(Long paymentId) {

        Payment payment = paymentRepository.findById(paymentId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Payment not found")
                );

        // If already success → just return (NO ERROR)
        if (payment.getStatus() == PaymentStatus.SUCCESS) {
            return;
        }

        //  If already failed → cannot succeed
        if (payment.getStatus() == PaymentStatus.FAILED) {
            throw new ConflictException("Payment already failed");
        }

        if (payment.getExpiresAt() != null &&
                payment.getExpiresAt().isBefore(LocalDateTime.now())) {

            handlePaymentFailure(paymentId);
            return;
        }

        Booking booking = payment.getBooking();

        if (booking.getStatus() != BookingStatus.PENDING_PAYMENT) {
            throw new ConflictException("Booking no longer valid");
        }

        payment.setStatus(PaymentStatus.SUCCESS);
        payment.setUpdatedAt(LocalDateTime.now());

        booking.setStatus(BookingStatus.CONFIRMED);

        for (Seat seat : booking.getSeats()) {
            seat.setSeatStatus(SeatStatus.BOOKED);
        }

        paymentRepository.save(payment);
        bookingRepository.save(booking);
    }

    @Override
    public void handlePaymentFailure(Long paymentId) {

        Payment payment = paymentRepository.findById(paymentId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Payment not found")
                );

        // ✅ If already failed → do nothing
        if (payment.getStatus() == PaymentStatus.FAILED) {
            return;
        }

        // ❌ If already success → cannot fail
        if (payment.getStatus() == PaymentStatus.SUCCESS) {
            throw new ConflictException("Payment already completed");
        }

        Booking booking = payment.getBooking();

        payment.setStatus(PaymentStatus.FAILED);
        payment.setUpdatedAt(LocalDateTime.now());

        if (booking.getStatus() == BookingStatus.PENDING_PAYMENT) {
            booking.setStatus(BookingStatus.CANCELLED);
            seatService.releaseSeats(booking.getSeats());
        }

        paymentRepository.save(payment);
        bookingRepository.save(booking);
    }

    @Override
    public void expireInitiatedPayments() {

        LocalDateTime now = LocalDateTime.now();

        List<Payment> expiredPayments =
                paymentRepository.findByStatusAndExpiresAtBefore(
                        PaymentStatus.INITIATED, now
                );

        for (Payment payment : expiredPayments) {

            Booking booking = payment.getBooking();

            payment.setStatus(PaymentStatus.FAILED);
            payment.setUpdatedAt(now);

            if (booking.getStatus() == BookingStatus.PENDING_PAYMENT) {
                booking.setStatus(BookingStatus.CANCELLED);
                seatService.releaseSeats(booking.getSeats());
            }
        }

        paymentRepository.saveAll(expiredPayments);
    }
}
