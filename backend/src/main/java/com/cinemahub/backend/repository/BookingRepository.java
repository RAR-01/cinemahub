package com.cinemahub.backend.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cinemahub.backend.model.Booking;
import com.cinemahub.backend.model.User;
import com.cinemahub.enums.BookingStatus;

import jakarta.persistence.LockModeType;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long>{

    List<Booking> findByStatusAndExpiresAtBefore(
        BookingStatus status,
        LocalDateTime time
    );

    List<Booking> findByUser(User user);

    // 🔒 Pessimistic Lock for payment initiation
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT b FROM Booking b WHERE b.id = :id")
    Optional<Booking> findByIdForUpdate(@Param("id") Long id);
}
