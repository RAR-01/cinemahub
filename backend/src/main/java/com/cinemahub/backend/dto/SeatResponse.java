package com.cinemahub.backend.dto;

import com.cinemahub.enums.SeatStatus;
import com.cinemahub.enums.SeatType;

public class SeatResponse {
    
    private Long id;
    private String seatNumber;
    private String rowLabel;
    private int columnNumber;
    private SeatType seatType;
    private SeatStatus seatStatus;
    private Double price;

    public SeatResponse(
            Long id,
            String seatNumber,
            String rowLabel,
            int columnNumber,
            SeatType seatType,
            SeatStatus seatStatus,
            Double price
    ) {
        this.id = id;
        this.seatNumber = seatNumber;
        this.rowLabel = rowLabel;
        this.columnNumber = columnNumber;
        this.seatType = seatType;
        this.seatStatus = seatStatus;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public String getSeatNumber() {
        return seatNumber;
    }

    public String getRowLabel() {
        return rowLabel;
    }

    public int getColumnNumber() {
        return columnNumber;
    }

    public SeatType getSeatType() {
        return seatType;
    }

    public SeatStatus getSeatStatus() {
        return seatStatus;
    }

    public Double getPrice() {
        return price;
    }

    
}
