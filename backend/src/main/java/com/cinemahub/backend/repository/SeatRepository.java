package com.cinemahub.backend.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import com.cinemahub.backend.model.Seat;
import com.cinemahub.enums.SeatStatus;

import jakarta.transaction.Transactional;

@Repository
@Transactional
public interface SeatRepository extends JpaRepository<Seat, Long> {
    
    List<Seat> findByScreenId(Long screenId);

    boolean existsByScreenIdAndSeatNumber(Long screenId, String seatNumber);

    void deleteByScreenId(Long screenId);

    List<Seat> findByIdInAndSeatStatus(List<Long> seatIDs, SeatStatus seatStatus);


    @Modifying
    @Query("""
        UPDATE Seat s
        SET s.seatStatus = :status,
            s.lockedAt = :lockedAt,
            s.lockExpiresAt = :expiresAt
        WHERE s.id IN :seatIds
    """)
    int lockSeats(
        @Param("seatIds")List<Long> seatIds,
        @Param("status")SeatStatus status,
        @Param("lockedAt")LocalDateTime lockedAt,
        @Param("expiresAt")LocalDateTime expiresAt
    );

    @Modifying
    @Query("""
        UPDATE Seat s
        SET s.seatStatus = :availableStatus,
            s.lockedAt = null,
            s.lockExpiresAt = null
        WHERE s.seatStatus = :lockedStatus
        AND s.lockExpiresAt < :now
            """)
    int releaseExpiredLocks(
        @Param("lockedStatus")  SeatStatus lockStatus,
        @Param("availableStatus")  SeatStatus availableStatus,
        @Param("now")  LocalDateTime now
    );
}
