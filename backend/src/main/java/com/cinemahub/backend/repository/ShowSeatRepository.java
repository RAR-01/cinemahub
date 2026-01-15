package com.cinemahub.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cinemahub.backend.model.Show;
import com.cinemahub.backend.model.ShowSeat;

public interface ShowSeatRepository extends JpaRepository<ShowSeat, Long> {

    List<ShowSeat> findByShow(Show show);
}
