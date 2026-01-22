package com.cinemahub.backend.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cinemahub.backend.model.Booking;
import com.cinemahub.backend.model.User;
import com.cinemahub.enums.BookingStatus;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long>{

    List<Booking> findByStatusAndExpiresAtBefore(
        BookingStatus status,
        LocalDateTime time
    );

    List<Booking> findByUser(User user);

}
