package com.cinemahub.backend.model;

import java.time.LocalDateTime;

import com.cinemahub.enums.SeatStatus;
import com.cinemahub.enums.SeatType;

import jakarta.persistence.*;


@Entity
@Table(name = "seats")
public class Seat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String seatNumber;

    @Column(nullable = false)
    private String rowLabel;

    @Column(nullable = false)
    private int columnNumber;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private SeatType seatType;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private SeatStatus seatStatus;

    @ManyToOne
    @JoinColumn(name = "screen_id", nullable = false)
    private Screen screen;

    @Column(name = "locked_at")
    private LocalDateTime lockedAt;

    @Column(name = "lock_expires_at")
    private LocalDateTime lockExpiresAt;

    @Column(nullable = false)
    private Double price; 
    
    public Seat() {}

    public Seat(String seatNumber, String rowLabel, int columnNumber,
                SeatType seatType, SeatStatus seatStatus, Screen screen, Double price) {
        this.seatNumber = seatNumber;
        this.rowLabel = rowLabel;
        this.columnNumber = columnNumber;
        this.seatType = seatType;
        this.seatStatus = seatStatus;
        this.screen = screen;
        this.price = price;
    }

    public long getId() {
        return id;
    }

    public String getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(String seatNumber) {
        this.seatNumber = seatNumber;
    }

    public String getRowLabel() {
        return rowLabel;
    }

    public void setRowLabel(String rowLabel) {
        this.rowLabel = rowLabel;
    }

    public int getColumnNumber() {
        return columnNumber;
    }

    public void setColumnNumber(int columnNumber) {
        this.columnNumber = columnNumber;
    }

    public SeatType getSeatType() {
        return seatType;
    }

    public void setSeatType(SeatType seatType) {
        this.seatType = seatType;
    }

    public SeatStatus getSeatStatus() {
        return seatStatus;
    }

    public void setSeatStatus(SeatStatus seatStatus) {
        this.seatStatus = seatStatus;
    }

    public Screen getScreen() {
        return screen;
    }

    public void setScreen(Screen screen) {
        this.screen = screen;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDateTime getLockedAt() {
        return lockedAt;
    }

    public void setLockedAt(LocalDateTime lockedAt) {
        this.lockedAt = lockedAt;
    }

    public LocalDateTime getLockExpiresAt() {
        return lockExpiresAt;
    }

    public void setLockExpiresAt(LocalDateTime lockExpiresAt) {
        this.lockExpiresAt = lockExpiresAt;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
    
}

