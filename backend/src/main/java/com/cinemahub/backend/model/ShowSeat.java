package com.cinemahub.backend.model;


import com.cinemahub.enums.SeatStatus;

import jakarta.persistence.*;

@Entity
public class ShowSeat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "show_id", nullable = false)
    private Show show;

    @ManyToOne
    @JoinColumn(name = "seat_id", nullable = false)
    private Seat seat;
    
    @Enumerated(EnumType.STRING)
    private SeatStatus seatStatus;

    public ShowSeat() {
    }

    public ShowSeat(Show show, Seat seat, SeatStatus seatStatus) {
        this.show = show;
        this.seat = seat;
        this.seatStatus = seatStatus;
    }

    public Show getShow() {
        return show;
    }

    public void setShow(Show show) {
        this.show = show;
    }

    public Seat getSeat() {
        return seat;
    }

    public void setSeat(Seat seat) {
        this.seat = seat;
    }

    public SeatStatus getSeatStatus() {
        return seatStatus;
    }

    public void setSeatStatus(SeatStatus seatStatus) {
        this.seatStatus = seatStatus;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
 
}
