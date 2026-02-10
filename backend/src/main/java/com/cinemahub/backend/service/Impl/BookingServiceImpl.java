package com.cinemahub.backend.service.Impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.StreamSupport;

import org.springframework.stereotype.Service;

import com.cinemahub.backend.exception.ConflictException;
import com.cinemahub.backend.exception.ResourceNotFoundException;
import com.cinemahub.backend.model.Booking;
import com.cinemahub.backend.model.Seat;
import com.cinemahub.backend.model.Show;
import com.cinemahub.backend.model.User;
import com.cinemahub.backend.repository.BookingRepository;
import com.cinemahub.backend.repository.SeatRepository;
import com.cinemahub.backend.repository.ShowRepository;
import com.cinemahub.backend.repository.UserRepository;
import com.cinemahub.backend.service.BookingService;
import com.cinemahub.backend.service.SeatLockService;
import com.cinemahub.backend.service.SeatService;
import com.cinemahub.enums.BookingStatus;
import com.cinemahub.enums.SeatStatus;

import jakarta.transaction.Transactional;
import jakarta.validation.constraints.NotNull;

@Service
@Transactional
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;
    private final ShowRepository showRepository;
    private final SeatRepository seatRepository;
    private final SeatLockService seatLockService;
    private final SeatService seatService;
    private final UserRepository userRepository;

    public BookingServiceImpl(
            BookingRepository bookingRepository,
            ShowRepository showRepository,
            SeatRepository seatRepository,
            SeatLockService seatLockService,
            SeatService seatService,
            UserRepository userRepository
    ) {
        this.bookingRepository = bookingRepository;
        this.showRepository = showRepository;
        this.seatRepository = seatRepository;
        this.seatLockService = seatLockService;
        this.seatService = seatService;
        this.userRepository = userRepository;
    }

    @Override
    public Booking createBooking(
            @NotNull Long showId,
            @NotNull List<Long> seatIds,
            @NotNull Long userId
    ) {

        expirePendingBookings();

        User user = userRepository.findById(userId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found")
                );

        Show show = showRepository.findById(showId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Show not found")
                );

        Iterable<Seat> seatIterable = seatRepository.findAllById(seatIds);
        List<Seat> seats = StreamSupport
                .stream(seatIterable.spliterator(), false)
                .toList();

        if (seats.size() != seatIds.size()) {
            throw new ResourceNotFoundException("One or more seats not found");
        }

        seatLockService.lockSeats(seatIds);

        Booking booking = new Booking();
        booking.setUser(user);
        booking.setShow(show);
        booking.setSeats(seats);
        booking.setStatus(BookingStatus.PENDING_PAYMENT);
        booking.setLockedAt(LocalDateTime.now());
        booking.setExpiresAt(LocalDateTime.now().plusMinutes(10));

        double totalAmount = seats.stream()
                .mapToDouble(Seat::getPrice)
                .sum();

        if (totalAmount <= 0) {
            throw new ConflictException("Invalid booking amount");
        }

        booking.setTotalAmount(totalAmount);

        return bookingRepository.save(booking);
    }

    @Override
    public Booking confirmBooking(Long bookingId) {

        expirePendingBookings();

        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Booking not found")
                );

        if (booking.getStatus() == BookingStatus.CONFIRMED) {
            return booking;
        }

        if (booking.getStatus() != BookingStatus.PENDING_PAYMENT) {
            throw new ConflictException("Booking is not eligible for payment");
        }

        for (Seat seat : booking.getSeats()) {
            if (seat.getSeatStatus() != SeatStatus.LOCKED) {
                throw new ConflictException("Seat lock lost");
            }
        }

        booking.setStatus(BookingStatus.CONFIRMED);

        for (Seat seat : booking.getSeats()) {
            seat.setSeatStatus(SeatStatus.BOOKED);
        }

        return bookingRepository.save(booking);
    }

    @Override
    public void expirePendingBookings() {

        LocalDateTime now = LocalDateTime.now();

        List<Booking> expiredBookings =
                bookingRepository.findByStatusAndExpiresAtBefore(
                        BookingStatus.PENDING_PAYMENT,
                        now
                );

        for (Booking booking : expiredBookings) {
            booking.setStatus(BookingStatus.CANCELLED);
            seatService.releaseSeats(booking.getSeats());
        }

        bookingRepository.saveAll(expiredBookings);
    }

    @Override
    public Booking getBookingById(Long bookingId) {
        return bookingRepository.findById(bookingId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Booking not found")
                );
    }
}
