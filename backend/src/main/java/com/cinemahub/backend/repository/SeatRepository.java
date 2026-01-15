package com.cinemahub.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import com.cinemahub.backend.model.Seat;

import jakarta.transaction.Transactional;

@Repository
@Transactional
public interface SeatRepository extends JpaRepository<Seat, Long> {
    
    List<Seat> findByScreenId(Long screenId);

    boolean existsByScreenIdAndSeatNumber(Long screenId, String seatNumber);

    void deleteByScreenId(Long screenId);

}
