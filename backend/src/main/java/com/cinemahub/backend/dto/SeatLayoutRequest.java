package com.cinemahub.backend.dto;

import com.cinemahub.enums.SeatType;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class SeatLayoutRequest {
    
    @NotNull(message = "Screen ID must not be null")
    private long screenId;
    @Min(value = 1, message = "Rows must be at least 1")
    private int rows;
    @Min(value = 1, message = "Columns must be at least 1")
    private int columns;
    @NotNull(message = "Seat type must not be null")
    private SeatType seatType;
    
    public long getScreenId() {
        return screenId;
    }
    
    public void setScreenId(long screenId) {
        this.screenId = screenId;
    }
    
    public int getRows() {
        return rows;
    }
    
    public void setRows(int rows) {
        this.rows = rows;
    }
    
    public int getColumns() {
        return columns;
    }
    
    public void setColumns(int columns) {
        this.columns = columns;
    }
    
    public SeatType getSeatType() {
        return seatType;
    }
    
    public void setSeatType(SeatType seatType) {
        this.seatType = seatType;
    }
 
}
