package com.cinemahub.backend.service.Impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.cinemahub.backend.dto.BookingHistoryDTO;
import com.cinemahub.backend.model.Booking;
import com.cinemahub.backend.model.Seat;
import com.cinemahub.backend.model.User;
import com.cinemahub.backend.repository.BookingRepository;
import com.cinemahub.backend.repository.UserRepository;
import com.cinemahub.backend.service.BookingHistoryService;
import com.cinemahub.enums.BookingStatus;

@Service
public class BookingHistoryServiceImpl implements BookingHistoryService {

    private final BookingRepository bookingRepository;
    private final UserRepository userRepository;

    public BookingHistoryServiceImpl(
            BookingRepository bookingRepository,
            UserRepository userRepository) {
        this.bookingRepository = bookingRepository;
        this.userRepository = userRepository;
    }

    // -------------------------------
    // GET ALL BOOKINGS OF A USER
    // -------------------------------
    @Override
    public List<BookingHistoryDTO> getBookingsByUserId(Long userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        List<Booking> bookings = bookingRepository.findByUser(user);

        List<BookingHistoryDTO> response = new ArrayList<>();

        bookings.sort((b1, b2) -> 
                b2.getCreatedAt().compareTo(b1.getCreatedAt()));

        for (Booking booking : bookings) {

                if (!isValidForHistory(booking)) {
                continue;
                }

                BookingHistoryDTO dto = mapToDTO(booking);
                response.add(dto);
        }

        return response;
    }


    // -------------------------------
    // GET SINGLE BOOKING DETAILS
    // -------------------------------
    @Override
    public BookingHistoryDTO getBookingDetails(Long bookingId, Long userId) {

        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new RuntimeException("Booking not found"));

        // ownership check (VERY IMPORTANT)
        if (!booking.getUser().getId().equals(userId)) {
            throw new RuntimeException("Unauthorized access to booking");
        }

        return mapToDTO(booking);
    }

    // -------------------------------
    // PRIVATE HELPERS
    // -------------------------------

    private boolean isValidForHistory(Booking booking) {
        return booking.getStatus() == BookingStatus.CONFIRMED
            || booking.getStatus() == BookingStatus.CANCELLED;
    }

    private BookingHistoryDTO mapToDTO(Booking booking) {

        BookingHistoryDTO dto = new BookingHistoryDTO();

        dto.setBookingId(booking.getId());
        dto.setStatus(booking.getStatus());
        dto.setTotalAmount(booking.getTotalAmount());
        dto.setBookedAt(booking.getCreatedAt());

        dto.setMovieTitle(
                booking.getShow()
                       .getMovie()
                       .getTitle()
        );

        dto.setTheatreName(
                booking.getShow()
                       .getScreen()
                       .getTheatre()
                       .getName()
        );

        dto.setScreenName(
                booking.getShow()
                       .getScreen()
                       .getName()
        );

        dto.setShowTime(
                booking.getShow().getStartTime()
        );

        dto.setSeatNumbers(
                booking.getSeats()
                       .stream()
                       .map(Seat::getSeatNumber)
                       .collect(Collectors.toList())
        );

        return dto;
    }
}
