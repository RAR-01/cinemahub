package com.cinemahub.backend.scheduler;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.cinemahub.backend.model.Show;
import com.cinemahub.backend.repository.ShowRepository;
import com.cinemahub.backend.repository.SeatRepository;
import com.cinemahub.enums.SeatStatus;
import com.cinemahub.enums.ShowStatus;

import jakarta.transaction.Transactional;

@Component
public class ShowCompletionScheduler {

    private final ShowRepository showRepository;
    private final SeatRepository seatRepository;

    public ShowCompletionScheduler(ShowRepository showRepository,
                                    SeatRepository seatRepository) {
        this.showRepository = showRepository;
        this.seatRepository = seatRepository;
    }

    @Scheduled(fixedRate = 60000)
    @Transactional
    public void completeFinishedShows() {

        LocalDateTime now = LocalDateTime.now().withNano(0);

        List<Show> finishedShows =
                showRepository.findByStatusAndEndTimeBefore(
                        ShowStatus.SCHEDULED,
                        now
                );

        for (Show show : finishedShows) {

            seatRepository.resetSeatsByScreenId(
                    show.getScreen().getId(),
                    SeatStatus.AVAILABLE
            );

            show.setStatus(ShowStatus.COMPLETED);

            System.out.println("Show " + show.getId()
                    + " completed. Seats reset.");
        }
    }
}
